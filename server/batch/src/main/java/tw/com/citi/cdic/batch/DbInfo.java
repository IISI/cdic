package tw.com.citi.cdic.batch;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;

/**
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class DbInfo {

    private String serverName;

    private String dbName;

    private String username;

    private String password;

    public DbInfo() throws ConfigurationException {
        Configuration config = new HierarchicalINIConfiguration("security.ini");
        String mode = config.getString("CONFIG.DEVELOPVERSION");
        serverName = config.getString(mode + ".SECServerName");
        dbName = config.getString(mode + ".SECDBName");
    }

    public String getServerName() {
        return serverName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
