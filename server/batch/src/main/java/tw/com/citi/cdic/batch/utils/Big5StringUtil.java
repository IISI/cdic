package tw.com.citi.cdic.batch.utils;

import java.io.UnsupportedEncodingException;

public class Big5StringUtil {
    public static String fitStringToLength(String orignal, int len) throws UnsupportedEncodingException {
        byte[] out = new byte[len];
        for (int i = 0; i < len; i++) {
            out[i] = (byte) 0x20;
        }
        orignal = orignal == null ? "" : orignal;
        int copyLen = len > orignal.getBytes("ms950").length ? orignal.getBytes("ms950").length : len;
        System.arraycopy(orignal.getBytes("ms950"), 0, out, 0, copyLen);
        return new String(out, "ms950");
    }
}
