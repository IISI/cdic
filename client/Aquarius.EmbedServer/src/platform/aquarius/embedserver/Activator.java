package platform.aquarius.embedserver;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "platform.aquarius.embedserver";

    // The shared instance
    private static Activator plugin;

    /**
     * The constructor
     */
    public Activator() {
    }

    /**
     * get OSGi Spring Context
     */
    public static ApplicationContext getSpringContext(String bundleId) {
        BundleContext bc = Platform.getBundle(Activator.PLUGIN_ID)
                .getBundleContext();
        ServiceReference sr = null;
        try {
            // use filter
            sr = Platform
                    .getBundle(Activator.PLUGIN_ID)
                    .getBundleContext()
                    .getServiceReferences(
                            ApplicationContext.class.getName(),
                            "(org.springframework.context.service.name="
                                    + Activator.PLUGIN_ID + ")")[0];
            return (ApplicationContext) bc.getService(sr);
        } catch (InvalidSyntaxException e) {
            LoggerFactory.getLogger(Activator.class).error(e.getMessage(), e);
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerFactory.getLogger(Activator.class).error(e.getMessage(), e);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

}
