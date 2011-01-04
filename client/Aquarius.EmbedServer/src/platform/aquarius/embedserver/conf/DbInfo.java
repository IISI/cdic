package platform.aquarius.embedserver.conf;

import java.net.URL;

import org.apache.commons.lang.StringUtils;
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
            if (StringUtils.isBlank(mode)) {
                throw new IllegalArgumentException("CONFIG.DEVELOPVERSION");
            }
            secServerName = config.getString(mode + ".SECServerName");
            if (StringUtils.isBlank(secServerName)) {
                throw new IllegalArgumentException(mode + ".SECServerName");
            }
            secDbName = config.getString(mode + ".SECDBName");
            if (StringUtils.isBlank(secDbName)) {
                throw new IllegalArgumentException(mode + ".SECDBName");
            }
            secPassword = PasswordUtil.decodePwd(config.getString(mode + ".SaDummy"));
            if (StringUtils.isBlank(secPassword)) {
                throw new IllegalArgumentException(mode + ".SaDummy");
            }
            appServerName = config.getString(mode + ".APPServerName");
            if (StringUtils.isBlank(appServerName)) {
                throw new IllegalArgumentException(mode + ".APPServerName");
            }
            appDbName = config.getString(mode + ".APPDBName");
            if (StringUtils.isBlank(appDbName)) {
                throw new IllegalArgumentException(mode + ".APPDBName");
            }
            appPassword = PasswordUtil.decodePwd(config.getString(mode + ".ApDummy"));
            if (StringUtils.isBlank(appPassword)) {
                throw new IllegalArgumentException(mode + ".ApDummy");
            }
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
