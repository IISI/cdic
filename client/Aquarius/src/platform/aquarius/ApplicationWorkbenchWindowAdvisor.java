package platform.aquarius;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import platform.aquarius.embedserver.jdbc.IDao;
import aquarius.Activator;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private boolean authed = false;

    public ApplicationWorkbenchWindowAdvisor(
            IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(
            IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }

    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1024, 768));
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(true);
        configurer.setShowPerspectiveBar(false);
        configurer.setShowFastViewBars(true);
        // configurer.setShowProgressIndicator(true);

        PlatformUI.getPreferenceStore().setValue(
                IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS,
                false);
        PlatformUI.getPreferenceStore().setValue(
                IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR,
                IWorkbenchPreferenceConstants.TOP_RIGHT);

    }

    @Override
    public void postWindowCreate() {
        super.postWindowCreate();
        
        Map<String, String> parameters = new HashMap<String, String>();
        StringBuilder passwordArgument = new StringBuilder();
        String[] args = Platform.getApplicationArgs();
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            if ("userId".equalsIgnoreCase(keyValue[0])) {
                parameters.put("userId", keyValue[1]);
            }
            if ("userPassword".equalsIgnoreCase(keyValue[0])) {
                passwordArgument.append(keyValue[1]);
            }
        }
        int additional = 64 - passwordArgument.length();
        for (int i = 0; i < additional; i++) {
            passwordArgument.append("0");
        }
        
        final LobHandler lobHandler = new DefaultLobHandler();
        List<Map<String, Object>> results = getDao().query("SUC001_QRY_USER_PASSWORD", new RowMapper<Map<String, Object>>() {

            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("password", lobHandler.getBlobAsBytes(rs, "USR_PWD_C"));
                return result;
            }}, parameters);
        
        if (results != null && results.size() > 0) {
            Map<String, Object> result = results.get(0);
            byte[] password = (byte[]) result.get("password");
            String encodedPassword = new String(Hex.encodeHex(password));
            if (encodedPassword.equalsIgnoreCase(passwordArgument.toString())) {
                authed = true;
            }
        }
        
        if (!authed) {
            MessageDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Access Denied", "User is not authorized to use this application.");
        }
    }

    @Override
    public void postWindowOpen() {
        super.postWindowOpen();
        if (!authed) {
            PlatformUI.getWorkbench().close();
        }
    }

    private IDao getDao() {
        BundleContext bc = Platform.getBundle(Activator.PLUGIN_ID).getBundleContext();
        ServiceReference daoRef = bc.getServiceReference(IDao.class.getName());
        IDao dao = (IDao) bc.getService(daoRef);
        return dao;
    }

}
