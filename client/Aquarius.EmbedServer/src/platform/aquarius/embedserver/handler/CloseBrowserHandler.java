package platform.aquarius.embedserver.handler;

import org.eclipse.ui.PlatformUI;

import platform.aquarius.embedserver.AbstractAquariusJavaHandler;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/22
 */
public class CloseBrowserHandler extends AbstractAquariusJavaHandler {

    @Override
    protected Object execute(Object[] args) {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true);
        return null;
    }

}
