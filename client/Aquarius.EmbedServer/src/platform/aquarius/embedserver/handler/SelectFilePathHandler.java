package platform.aquarius.embedserver.handler;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.PlatformUI;

import platform.aquarius.embedserver.AbstractAquariusJavaHandler;

public class SelectFilePathHandler extends AbstractAquariusJavaHandler {

    @Override
    protected Object execute(Object[] args) {
        DirectoryDialog fd = new DirectoryDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
        fd.setMessage("請選擇存放檔案的路徑");
        fd.setText("檔案下載");
        return fd.open();
    }

}
