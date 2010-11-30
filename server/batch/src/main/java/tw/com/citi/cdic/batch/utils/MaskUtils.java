package tw.com.citi.cdic.batch.utils;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/22
 */
public class MaskUtils {

    public static String mask(String str, int splitPos) {
        return mask(str, splitPos, "X");
    }

    public static String mask(String str, int splitPos, String mask) {
        if (str == null) {
            return str;
        }
        
        if (splitPos >= str.length()) {
            return str;
        }
        
        int len = str.length() - splitPos;
        StringBuilder maskStr = new StringBuilder();
        for (int i = 0; i < len; i++) {
            maskStr.append(mask);
        }
        
        StringBuilder sb = new StringBuilder(str);
        sb.replace(splitPos, sb.length(), maskStr.toString());
        return sb.toString();
    }

}
