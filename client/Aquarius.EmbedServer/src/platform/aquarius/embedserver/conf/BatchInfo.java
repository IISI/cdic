package platform.aquarius.embedserver.conf;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.runtime.Platform;

public class BatchInfo {
    private String serviceUrl;
    private static Properties config;

    public BatchInfo() {
        config = new Properties();
        try {
            URL url = Platform.getBundle("tw.com.citi.cdic.client.resources").getResource("batch.properties");
            config.load(url.openStream());
            serviceUrl = config.getProperty("serviceUrl");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Load batch setting error.", e);
        }
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }
}