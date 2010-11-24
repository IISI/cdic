package platform.aquarius.embedserver.handler;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "platform.aquarius.embedserver.handler.messages"; //$NON-NLS-1$
    public static String SelectFilePathHandler_0;
    public static String SelectFilePathHandler_1;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
