package tw.com.citi.cdic.client;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.conf.PasswordUtil;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/25
 */
public class BatchServiceImpl implements BatchService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private String hostname;

    private String username;

    private String password;

    private String progpath;

    public BatchServiceImpl() {
        Properties config = new Properties();
        try {
            URL url = Platform.getBundle("tw.com.citi.cdic.client.resources").getResource("batchService.properties");
            config.load(url.openStream());
            this.hostname = config.getProperty("hostname");
            this.username = config.getProperty("username");
            this.password = PasswordUtil.decodePwd(config.getProperty("password"));
            this.progpath = config.getProperty("progpath");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callRemote(String cmd, String... args) throws Exception {
        URL resourceUrl = Platform.getBundle("tw.com.citi.cdic.client.resources").getResource("triggerBatch.exe");
        if (resourceUrl != null) {
            try {
                URL fileUrl = FileLocator.toFileURL(resourceUrl);
                File file = new File(fileUrl.toURI());
                StringBuilder sb = new StringBuilder();
                sb.append(file.getAbsolutePath());
                sb.append(" /accepteula \\\\").append(hostname);
                sb.append(" -u ").append(username);
                sb.append(" -p ").append(password);
                sb.append(" -d \"").append(cmd).append("\"");
                for (String arg : args) {
                    sb.append(" \"").append(arg).append("\"");
                }
                Runtime.getRuntime().exec(sb.toString());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw e;
            }
        } else {
            logger.error("Can not find file: triggerBatch.exe");
        }
    }

    @Override
    public void init() throws Exception {
        callRemote(progpath + "/launch.cmd", "copyViewJob", "schedule.timestamp(long)=" + new Date().getTime());
    }

    @Override
    public void launch() throws Exception {
        callRemote(progpath + "/launch.cmd", "convertJob", "schedule.timestamp(long)=" + new Date().getTime());
    }

}
