package platform.aquarius.embedserver.conf;

import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.eclipse.core.runtime.Platform;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/13
 */
public class DbInfo {

    private String secServerName;

    private String secDbName;

    private String secPassword;

    private String appServerName;

    private String appDbName;

    private String appPassword;

    public DbInfo() {
        try {
            URL url = Platform.getBundle("platform.aquarius.resources").getResource("security.ini");
            HierarchicalINIConfiguration config = new HierarchicalINIConfiguration(url);

            String mode = config.getString("CONFIG.DEVELOPVERSION");
            secServerName = config.getString(mode + ".SECServerName");
            secDbName = config.getString(mode + ".SECDBName");
            secPassword = PasswordUtil.decodePwd(config.getString(mode + ".SaDummy"));
            appServerName = config.getString(mode + ".APPServerName");
            appDbName = config.getString(mode + ".APPDBName");
            appPassword = PasswordUtil.decodePwd(config.getString(mode + ".ApDummy"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getSecServerName() {
        return secServerName;
    }

    public String getSecDbName() {
        return secDbName;
    }

    public String getSecPassword() {
        return secPassword;
    }

    public String getAppServerName() {
        return appServerName;
    }

    public String getAppDbName() {
        return appDbName;
    }

    public String getAppPassword() {
        return appPassword;
    }

}
