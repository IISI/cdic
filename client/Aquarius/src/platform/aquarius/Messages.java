package platform.aquarius;

import org.eclipse.osgi.util.NLS;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/21
 */
public class Messages extends NLS {
    private static final String BUNDLE_NAME = "platform.aquarius.messages"; //$NON-NLS-1$
    public static String BrowserEditorPart_Copy;
    public static String BrowserEditorPart_Loading;
    public static String BrowserEditorPart_Reload;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
