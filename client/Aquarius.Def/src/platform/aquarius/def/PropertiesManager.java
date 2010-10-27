package platform.aquarius.def;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.runtime.Platform;

public class PropertiesManager {

    public static Properties getSqlProperties() throws IOException {
        URL resourceUrl = Platform.getBundle("platform.aquarius.def")
                .getResource("SQLCommand.xml");
        Properties props = new Properties();
        props.loadFromXML(resourceUrl.openStream());
        return props;
    }

}
