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

    private String serverPort;

    private String dbName;

    private String parameters;

    private String username;

    private String password;

    public DbInfo() throws ConfigurationException {
        Configuration config = new HierarchicalINIConfiguration("database.ini");
        String mode = config.getString("CONFIG.DEVELOPVERSION");
        serverName = config.getString(mode + ".ServerName");
        serverPort = config.getString(mode + ".ServerPort");
        dbName = config.getString(mode + ".DBName");
        parameters = config.getString(mode + ".Parameters", "");
        username = config.getString(mode + ".Username");
        password = config.getString(mode + ".Password");
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getParameters() {
        return parameters;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
