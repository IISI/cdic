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

    private void callRemote(String cmd) throws Exception {
        URL resourceUrl = Platform.getBundle("tw.com.citi.cdic.client").getResource("PsExec.exe");
        if (resourceUrl != null) {
            try {
                URL fileUrl = FileLocator.toFileURL(resourceUrl);
                File file = new File(fileUrl.toURI());
                Runtime.getRuntime().exec(file.getAbsolutePath() + " /accepteula \\\\" + hostname + " -u " + username + " -p " + password + " -d \"" + cmd + "\"");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw e;
            }
        }
    }

    @Override
    public void init() throws Exception {
        callRemote("C:/Documents and Settings/Administrator/My Documents/batch.cmd");
    }

    @Override
    public void launch() throws Exception {
        callRemote("C:/Documents and Settings/Administrator/My Documents/batch.cmd");
    }

}
