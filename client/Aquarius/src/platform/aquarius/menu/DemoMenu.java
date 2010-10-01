package platform.aquarius.menu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class DemoMenu extends Action implements IWorkbenchAction {

    public DemoMenu() {
    }

    @Override
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getShell();
        String dialogBoxTitle = "Message";
        String message = "You clicked the custom action from the menu!";
        MessageDialog.openInformation(shell, dialogBoxTitle, message);
    }

    @Override
    public void dispose() {

    }

}
