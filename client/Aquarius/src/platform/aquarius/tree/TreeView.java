package platform.aquarius.tree;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;

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

import platform.aquarius.browser.BrowserEditorInput;
import platform.aquarius.browser.BrowserEditorPart;
import aquarius.Activator;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class TreeView extends ViewPart {

    public static final String ID = "platform.aquarius.tree.TreeView";

    private TreeViewer viewer;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createPartControl(Composite parent) {
        this.setPartName("系統功能(一)");
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
                                    new BrowserEditorInput(),
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
                    config.openStream());
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(JsonArray.class,
                    new CustomerDeserializer());
            JsonArray json = gsonBuilder.create().fromJson(reader,
                    JsonArray.class);

            viewer.setInput(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // viewer.expandAll();
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
        @Override
        public JsonArray deserialize(JsonElement element, Type type,
                JsonDeserializationContext context) throws JsonParseException {
            return element.getAsJsonArray();
        }
    }
}
