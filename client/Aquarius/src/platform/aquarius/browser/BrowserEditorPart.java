package platform.aquarius.browser;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.part.EditorPart;

import platform.aquarius.Messages;
import platform.aquarius.embedserver.CustomFunction;

/**
 * 
 * @author Tony
 * 
 */
public class BrowserEditorPart extends EditorPart {

    public static final String ID = "Aquarius.Browser.View"; //$NON-NLS-1$

    public Browser browser;

    // private String initialUrl = "";

    /*
	 * 
	 */
    public BrowserEditorPart() {
        // initialUrl = "";
        // BrowserPlugin.getDefault().getPluginPreferences().getString(IBrowserConstants.PREF_HOME_PAGE);
        // initialUrl = "http://127.0.0.1:8080/app/";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.ViewPart#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
     * .Composite)
     */
    public void createPartControl(Composite parent) {
        browser = createBrowser(parent);
        // browser.setUrl(initialUrl);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    public void setFocus() {
    }

    /*
	 * 
	 */
    private Browser createBrowser(Composite parent) {
        browser = new Browser(parent, SWT.MOZILLA);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        parent.setLayout(gridLayout);

        GridData data = new GridData();
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        data.horizontalSpan = 1;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        browser.setLayoutData(data);

        browser.addTitleListener(new TitleListener() {
            public void changed(TitleEvent event) {
                setPartName(event.title);
            }
        });

        browser.addOpenWindowListener(new OpenWindowListener() {
            public void open(WindowEvent event) {
            }
        });

        // nsIWebBrowser nsBro = (nsIWebBrowser) browser.getWebBrowser();

        // nsBro.addWebBrowserListener(new Weblis(),
        // nsIWebProgressListener.NS_IWEBPROGRESSLISTENER_IID);

        // browser.addStatusTextListener(new StatusTextListener() {
        // IStatusLineManager status = getViewSite().getActionBars()
        // .getStatusLineManager();
        //
        // public void changed(StatusTextEvent event) {
        // // status.setMessage(event.text);
        // }
        // });
        browser.addOpenWindowListener(new OpenWindowListener() {
            public void open(WindowEvent event) {
                BrowserEditorPart.this.openWindow(event);
            }
        });
        browser.addProgressListener(new ProgressAdapter() {

            IProgressMonitor monitor = getEditorSite().getActionBars()
                    .getStatusLineManager().getProgressMonitor();

            IStatusLineManager status = getEditorSite().getActionBars()
                    .getStatusLineManager();

            boolean working = false;
            int workedSoFar;

            public void changed(ProgressEvent event) {
                if (event.total == 0 || event.total == -1)
                    return;
                if (!working) {
                    if (event.current == event.total)
                        return;
                    monitor.beginTask(Messages.BrowserEditorPart_Loading, event.current);
                    workedSoFar = 0;
                    working = true;
                }
                status.find(BrowserProgressIcon.ID).setVisible(true);
                monitor.worked(event.total - workedSoFar);
                workedSoFar = event.total;
            }

            public void completed(ProgressEvent event) {
                monitor.done();
                working = false;
                status.find(BrowserProgressIcon.ID).setVisible(false);
            }
        });

        new CustomFunction(browser, "callJava"); //$NON-NLS-1$

        Menu popupMenu = new Menu(parent);
        MenuItem mi = new MenuItem(popupMenu, SWT.DEFAULT);
        mi.setText(Messages.BrowserEditorPart_Reload);
        mi.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                browser.refresh();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        mi = new MenuItem(popupMenu, SWT.DEFAULT);
        mi.setText(Messages.BrowserEditorPart_Copy);
        mi.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                browser.execute("__copyToClipboard();"); //$NON-NLS-1$
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        // browser.setMenu(popupMenu);

        return browser;
    }

    /**
     * Opens a new browser window.
     * 
     * @param event
     *            the open window event
     */
    private void openWindow(WindowEvent event) {
        try {
            IWorkbench workbench = getSite().getWorkbenchWindow()
                    .getWorkbench();
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
                    .getActivePage();

            Shell shell = workbench.getActiveWorkbenchWindow().getShell();
            if (event.location != null)
                shell.setLocation(event.location);
            if (event.size != null)
                shell.setLocation(event.size);

            BrowserEditorPart part = (BrowserEditorPart) page.openEditor(
                    new BrowserEditorInput(), BrowserEditorPart.ID);

            Assert.isNotNull(part);
            event.browser = part.browser;
        } catch (WorkbenchException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // 以下為 IEditorPart的Method
    // ---------------------------

    @Override
    public void doSave(IProgressMonitor arg0) {
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public void init(IEditorSite site, IEditorInput input)
            throws PartInitException {
        setSite(site);
        setInput(input);
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

}
