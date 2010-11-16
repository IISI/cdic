package tw.com.citi.cdic.utils;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "tw.com.citi.cdic.utils.messages"; //$NON-NLS-1$
    public static String STATUS_0;
    public static String STATUS_1;
    public static String STATUS_2;
    public static String STATUS_3;
    public static String STATUS_4;
    public static String STATUS_5;
    public static String STATUS_6;
    public static String SUC002Handler_InitStateError;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
