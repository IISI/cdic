package platform.aquarius;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import platform.aquarius.browser.BrowserProgressIcon;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    // private MenubarUtil mbUtil;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui
     * .IWorkbenchWindow)
     */
    protected void makeActions(final IWorkbenchWindow window) {
        // mbUtil = new MenubarUtil();
        // for (Action act : mbUtil.getActions()) {
        // register(act);
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface
     * .action.IMenuManager)
     */
    protected void fillMenuBar(IMenuManager menuBar) {
        // for (MenuManager mgr : mbUtil.getMenuManagers()) {
        // menuBar.add(mgr);
        // }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.application.ActionBarAdvisor#fillCoolBar(org.eclipse.jface
     * .action.ICoolBarManager)
     */
    protected void fillCoolBar(ICoolBarManager coolBar) {
        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));
        toolbar.add(new Action() {

            @Override
            public void run() {

            }

            @Override
            public ImageDescriptor getImageDescriptor() {
                return new ImageDescriptor() {

                    @Override
                    public ImageData getImageData() {
                        Image img = new Image(PlatformUI.getWorkbench()
                                .getDisplay(), this.getClass()
                                .getResourceAsStream("ProgressIcon.png"));
                        return img.getImageData();
                    }
                };
            }

            @Override
            public String getText() {
                return "";
            }

        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.application.ActionBarAdvisor#fillStatusLine(org.eclipse
     * .jface.action.IStatusLineManager)
     */
    @Override
    protected void fillStatusLine(IStatusLineManager statusLine) {
        super.fillStatusLine(statusLine);
        IContributionItem loadingItem = new BrowserProgressIcon(
                BrowserProgressIcon.ID);

        statusLine.add(loadingItem);
        statusLine.update(true);
    }
}
