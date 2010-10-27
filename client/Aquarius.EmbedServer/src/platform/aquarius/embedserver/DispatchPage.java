package platform.aquarius.embedserver;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import platform.aquarius.embedserver.pages.AbstractAquariusPage;

public class DispatchPage extends AbstractAquariusPage {

    @SuppressWarnings("unchecked")
    public DispatchPage(PageParameters parameters) {
        String pageName = "platform.aquarius.embedserver.pages."
                + parameters.getString("pageName");
        String bundleId = parameters.getString("bundleId");
        String bundleVersion = parameters.getString("bundleVersion");

        try {
            Class<? extends Page> page = null;
            if ("".equals(bundleVersion == null ? "" : bundleVersion)) {
                Bundle b = Platform.getBundle(bundleId);
                page = b.loadClass(pageName);
            } else {
                Bundle[] bs = Platform.getBundles(bundleId, bundleVersion);
                if (bs != null) {
                    Bundle b = bs[0];
                    if (!bundleVersion.equals(b.getVersion().toString())) {
                        // TODO 版本不合，報錯

                    } else {
                        page = b.loadClass(pageName);
                    }
                } else {
                    // TODO 找不到

                }
            }

            setResponsePage(page, parameters);
        } catch (ClassNotFoundException e) {
            logError(e.getMessage(), e);
        }
    }

}
