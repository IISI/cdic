package tw.com.citi.cdic.client;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/25
 */
public class BatchServiceImpl implements BatchService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private String hostname;

    private String username;

    private String password;

    public BatchServiceImpl() {
        this.hostname = "bath";
        this.username = "Administrator";
        this.password = "p@ssw0rd";
    }

    private void callRemote(String cmd, String... args) throws Exception {
        URL resourceUrl = Platform.getBundle("tw.com.citi.cdic.client").getResource("PsExec.exe");
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
        }
    }

    @Override
    public void init() throws Exception {
        callRemote("C:/Documents and Settings/Administrator/My Documents/batch-1.0-SNAPSHOT/launch.cmd", "copyViewJob");
    }

    @Override
    public void launch() throws Exception {
        callRemote("C:/Documents and Settings/Administrator/My Documents/batch-1.0-SNAPSHOT/launch.cmd", "convertJob");
    }

}
