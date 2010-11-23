package tw.com.citi.cdic.batch;

import java.io.IOException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import tw.com.citi.cdic.batch.utils.HierarchicalINIConfiguration;
import tw.com.citi.cdic.batch.utils.PasswordUtil;

/**
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class DbInfo {

    protected static final Logger logger = LoggerFactory.getLogger(DbInfo.class);

    private String serverName;

    private String serverPort;

    private String dbName;

    private String parameters;

    private String username;

    private String password;

    public DbInfo() throws ConfigurationException, IOException {
        Resource resource = new ClassPathResource("database.ini");
        logger.debug("database.ini exists = {}", resource.exists());
        Configuration config = new HierarchicalINIConfiguration(resource.getFile());
        String mode = config.getString("CONFIG.DEVELOPVERSION");
        serverName = config.getString(mode + ".ServerName");
        serverPort = config.getString(mode + ".ServerPort");
        dbName = config.getString(mode + ".DBName");
        parameters = config.getString(mode + ".Parameters", "");
        username = config.getString(mode + ".Username");
        password = PasswordUtil.decodePwd(config.getString(mode + ".Password"));
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
