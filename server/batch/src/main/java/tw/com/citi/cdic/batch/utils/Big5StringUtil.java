package tw.com.citi.cdic.batch.utils;

import java.io.UnsupportedEncodingException;

public class Big5StringUtil {
    public static String fitStringToLength(String orignal, int len) throws UnsupportedEncodingException {
        byte[] out = new byte[len];
        for (int i = 0; i < len; i++) {
            out[i] = (byte) 0x20;
        }
        orignal = orignal == null ? "" : orignal;
        int copyLen = len > orignal.getBytes("big5").length ? orignal.getBytes("big5").length : len;
        System.arraycopy(orignal.getBytes("big5"), 0, out, 0, copyLen);
        return new String(out, "big5");
    }
}
