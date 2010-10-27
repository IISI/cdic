package platform.aquarius.embedserver;

import java.net.URL;
import java.net.URLConnection;

import org.eclipse.jetty.util.resource.URLResource;

public class OSGiBundleResource extends URLResource {

    public OSGiBundleResource(URL url, URLConnection connection) {
        super(url, connection);
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

}
