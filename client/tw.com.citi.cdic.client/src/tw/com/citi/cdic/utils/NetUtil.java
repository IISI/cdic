package tw.com.citi.cdic.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUtil {

    protected static final Logger logger = LoggerFactory.getLogger(NetUtil.class);

    public static String getWins() {
        try {
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            InputStream in = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "MS950"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("Primary WINS Server")) {
                    String[] wins = line.split(":");
                    return wins[1].trim();
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("Failed to execute command: ipconfig /all", e);
            throw new RuntimeException(e);
        }
    }

}
