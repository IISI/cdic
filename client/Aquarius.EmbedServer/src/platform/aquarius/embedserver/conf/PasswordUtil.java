package platform.aquarius.embedserver.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.configuration.ConfigurationException;

public class PasswordUtil {
    public static String decodePwd(String cipher) throws ConfigurationException {
        StringBuffer plain = new StringBuffer();
        if (cipher == null || "".equals(cipher.trim())) {
            throw new ConfigurationException("Parameter missing.");
        }
        StringTokenizer st = new StringTokenizer(cipher, ";");
        int count = st.countTokens();
        if (count <= 0) {
            throw new ConfigurationException("Parameter invalid.");
        }
        List<String> tokens = new ArrayList<String>();
        int sum = 0;
        int j = 1;
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        for (int i = count; i > 0; i--) {
            sum = Integer.parseInt(tokens.get(i - 1)) - 128 - j;
            if (sum < 0) {
                sum += 256;
            }
            char c = new Character((char) sum).charValue();

            plain = plain.append(Pattern.compile("[\\p{Alnum}\\p{Punct}]").matcher(String.valueOf(c)).matches() ? c
                    : "");
            j++;
        }
        return plain.toString();
    }

    public static String encodePwd(String plain) throws ConfigurationException {
        StringBuffer cipher = new StringBuffer();
        int[] b = new int[40];
        for (int i = 0; i < plain.length(); i++) {
            b[39 - i * 2] = plain.charAt(i);
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = (b[i] == 0 ? i % 2 == 0 ? 0 : 32 : b[i]) + 128 + b.length - i;
            cipher.append(String.valueOf(b[i])).append(i == b.length - 1 ? "" : ";");
        }
        return cipher.toString();
    }

    public static void main(String[] args) throws Exception {
        // System.out
        // .println(decodePwd("168;199;166;197;164;195;162;193;160;191;158;189;156;187;154;185;152;183;150;181;148;179;146;177;144;175;142;173;140;171;138;169;136;167;134;200;132;200;130;212"));
        // String enc = encodePwd("SEC");
        // System.out.println(enc);
        // System.out
        // .println(enc
        // .equals("168;199;166;197;164;195;162;193;160;191;158;189;156;187;154;185;152;183;150;181;148;179;146;177;144;175;142;173;140;171;138;169;136;167;134;200;132;200;130;212"));
        // System.out.println(decodePwd(enc));
        // System.out.println(encodePwd("p@ssw0rd"));
        if (args != null && args.length > 0) {
            System.out.println("input : " + args[0]);
            System.out.println("output: " + encodePwd(args[0]));
        }
    }
}
