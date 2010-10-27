package platform.aquarius.embedserver.handler;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

import platform.aquarius.embedserver.AbstractAquariusJavaHandler;

public class SelectFileHandler extends AbstractAquariusJavaHandler {

    @Override
    protected Object execute(Object[] args) {
        FileDialog fd = new FileDialog(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell());
        return fd.open();
    }

}
