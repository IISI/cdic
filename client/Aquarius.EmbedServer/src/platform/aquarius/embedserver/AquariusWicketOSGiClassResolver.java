package platform.aquarius.embedserver;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.application.DefaultClassResolver;
import org.apache.wicket.application.IClassResolver;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * AquariusWicketOSGiClassResolver
 * 
 * @author Tony
 * 
 */
public class AquariusWicketOSGiClassResolver implements IClassResolver {

    private final ConcurrentHashMap<String, WeakReference<Class<?>>> classes = new ConcurrentHashMap<String, WeakReference<Class<?>>>();

    public final Class<?> resolveClass(final String classname)
            throws ClassNotFoundException {
        Class<?> clazz = null;

        WeakReference<Class<?>> ref = classes.get(classname);

        // Might be garbage-collected between getting the WeakRef and retrieving
        // the Class from it.
        if (ref != null) {
            clazz = ref.get();
        }
        if (clazz == null) {
            if (classname.equals("byte")) {
                clazz = byte.class;
            } else if (classname.equals("short")) {
                clazz = short.class;
            } else if (classname.equals("int")) {
                clazz = int.class;
            } else if (classname.equals("long")) {
                clazz = long.class;
            } else if (classname.equals("float")) {
                clazz = float.class;
            } else if (classname.equals("double")) {
                clazz = double.class;
            } else if (classname.equals("boolean")) {
                clazz = boolean.class;
            } else if (classname.equals("char")) {
                clazz = char.class;
            } else {
                synchronized (classes) {
                    ClassLoader loader = Thread.currentThread()
                            .getContextClassLoader();
                    if (loader == null) {
                        loader = DefaultClassResolver.class.getClassLoader();
                    }
                    try {
                        clazz = loader.loadClass(classname);
                    } catch (Exception e) {
                        // 開始使用IPageResolverService尋找Class
                        try {
                            ServiceReference[] services = Platform
                                    .getBundle(Activator.PLUGIN_ID)
                                    .getBundleContext()
                                    .getServiceReferences(
                                            IPageResolverService.class
                                                    .getName(),
                                            null);
                            for (ServiceReference s : services) {
                                try {
                                    clazz = s.getBundle().loadClass(classname);
                                } catch (ClassNotFoundException ex) {
                                    // Ignore
                                }
                                if (clazz != null)
                                    break;
                            }
                        } catch (InvalidSyntaxException ex) {
                            // Ignore
                        }
                    }
                }
                classes.put(classname, new WeakReference<Class<?>>(clazz));
            }
        }
        return clazz;
    }

    public Iterator<URL> getResources(String name) {
        HashSet<URL> loadedFiles = new HashSet<URL>();
        try {
            // Try the classloader for the wicket jar/bundle
            Enumeration<URL> resources = Application.class.getClassLoader()
                    .getResources(name);
            loadResources(resources, loadedFiles);

            // Try the classloader for the user's application jar/bundle
            resources = Application.get().getClass().getClassLoader()
                    .getResources(name);
            loadResources(resources, loadedFiles);

            // Try the context class loader
            resources = Thread.currentThread().getContextClassLoader()
                    .getResources(name);
            loadResources(resources, loadedFiles);
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }

        return loadedFiles.iterator();
    }

    private void loadResources(Enumeration<URL> resources, Set<URL> loadedFiles) {
        if (resources != null) {
            while (resources.hasMoreElements()) {
                final URL url = resources.nextElement();
                if (!loadedFiles.contains(url)) {
                    loadedFiles.add(url);
                }
            }
        }
    }

}
