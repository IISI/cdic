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
    public static String SUC002Handler_BatchServiceNotAvaliable;
    public static String SUC005Handler_InitFirst;
    public static String Access_Host_File_Error;
    public static String Download_Local_File_Error;
    public static String Upload_Local_File_Error;
    public static String Get_Local_File_Info_Error;
    public static String Download_Sample_File_Error;
    public static String Handler_Params_Error;
    public static String Handler_Action_Error;
    public static String Start_Batch_Error;
    public static String COPY_CDIC_File_Error;
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
