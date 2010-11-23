package platform.aquarius.tree;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.jdbc.core.RowMapper;

import platform.aquarius.browser.BrowserEditorInput;
import platform.aquarius.browser.BrowserEditorPart;
import platform.aquarius.embedserver.jdbc.IDao;
import aquarius.Activator;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class TreeView extends ViewPart {

    public static final String ID = "platform.aquarius.tree.TreeView";

    private TreeViewer viewer;

    private BrowserEditorInput editorInput = new BrowserEditorInput();

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent);
        // viewer.getTree().setLinesVisible(true);
        viewer.setContentProvider(new FileTreeContentProvider());
        viewer.setLabelProvider(new TreeViewLabelProvider());
        viewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent ev) {
                TreeSelection tree = (TreeSelection) ev.getSelection();
                JsonElement jo = (JsonElement) tree.getFirstElement();
                JsonElement url = jo.getAsJsonObject().get("url");
                if (url != null) {
                    IWorkbenchPage page = PlatformUI.getWorkbench()
                            .getActiveWorkbenchWindow().getActivePage();
                    if (page != null) {
                        try {
                            IEditorPart part = page.openEditor(
                                    editorInput,
                                    BrowserEditorPart.ID);

                            if (part instanceof BrowserEditorPart) {
                                ((BrowserEditorPart) part).browser.forceFocus();
                                ((BrowserEditorPart) part).browser.setUrl(url
                                        .getAsString());
                            }
                        } catch (PartInitException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        // 使用Config File做為TreeView設定來源
        URL config = Platform.getBundle(Activator.PLUGIN_ID).getResource(
                "/configs/tree.json");
        try {
            InputStreamReader reader = new InputStreamReader(
                    config.openStream(), "UTF-8");
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(JsonArray.class,
                    new CustomerDeserializer());
            JsonArray json = gsonBuilder.create().fromJson(reader,
                    JsonArray.class);

            viewer.setInput(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewer.expandAll();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    private class CustomerDeserializer implements JsonDeserializer<JsonArray> {

        private List<AppFunction> functionList = null;

        @Override
        public JsonArray deserialize(JsonElement element, Type type,
                JsonDeserializationContext context) throws JsonParseException {
            return filter(element.getAsJsonArray());
        }

        private JsonArray filter(JsonArray array) {
            JsonArray array2 = new JsonArray();
            for (Iterator<JsonElement> iter = array.iterator(); iter.hasNext();) {
                JsonObject function = iter.next().getAsJsonObject();
                boolean allowed = false;
                JsonElement id = function.get("id");
                if (id != null) {
                    for (AppFunction func : getFunctionList()) {
                        if (id.getAsString().equals(func.getFunctionId())) {
                            allowed = true;
                            break;
                        }
                    }
                    
                    if (allowed) {
                        if (function.has("node")) {
                            JsonElement node = function.get("node");
                            JsonArray array3 = filter(node.getAsJsonArray());
                            if (array3.size() > 0) {
                                function.add("node", array3);
                                array2.add(function);
                            }
                        } else {
                            array2.add(function);
                        }
                    }
                } else {
                    if (function.has("node")) {
                        JsonElement node = function.get("node");
                        JsonArray array3 = filter(node.getAsJsonArray());
                        if (array3.size() > 0) {
                            function.add("node", array3);
                            array2.add(function);
                        }
                    }
                }
            }
            return array2;
        }

        private IDao getDao() {
            BundleContext bc = Platform.getBundle(Activator.PLUGIN_ID).getBundleContext();
            ServiceReference daoRef = bc.getServiceReference(IDao.class.getName());
            IDao dao = (IDao) bc.getService(daoRef);
            return dao;
        }

        private List<AppFunction> getFunctionList() {
            if (functionList != null) {
                return functionList;
            }
            
            String[] args = Platform.getApplicationArgs();
            Map<String, String> params = new HashMap<String, String>();
            for (String arg : args) {
                String[] keyValue = arg.split("=", 2);
                if ("userId".equalsIgnoreCase(keyValue[0])) {
                    params.put("userId", keyValue[1]);
                }
                if ("systemId".equalsIgnoreCase(keyValue[0])) {
                    params.put("sysId", keyValue[1]);
                }
            }
            
            return getDao().query("SUC001_QRY_USERRIGHT", new RowMapper<AppFunction>() {

                @Override
                public AppFunction mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    AppFunction func = new AppFunction();
                    func.setFunctionId(rs.getString("fun_id_c"));
                    func.setAddRight("V".equals(rs.getString("add_rght_f")));
                    func.setChangeRight("V".equals(rs.getString("cha_rght_f")));
                    func.setDeleteRight("V".equals(rs.getString("del_rght_f")));
                    func.setInqueryRight("V".equals(rs.getString("inq_rght_f")));
                    func.setExecuteRight("V".equals(rs.getString("exe_rght_f")));
                    func.setPrimaryRight("V".equals(rs.getString("pri_rght_f")));
                    func.setSubMenu(rs.getString("SubMenu"));
                    func.setItemSeq(rs.getString("ItemSeq"));
                    func.setFunctionName(rs.getString("fun_name_c"));
                    return func;
                }
            }, params);
        }

    }

}
