package platform.aquarius.embedserver;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.osgi.framework.Bundle;

import platform.aquarius.embedserver.jdbc.IDao;

public class CustomFunction extends BrowserFunction {

    private IDao dao;

    public CustomFunction(Browser browser, String name) {
        super(browser, name);
        dao = Activator.getSpringContext(Activator.PLUGIN_ID).getBean(
                IDao.class);
    }

    /**
     * 呼叫這個方法時，至少要帶三個參數，第一個是 bundle 的名稱，第二個是 bundle 的版本(空字串代表使用最新版本)，第三個則是 class
     * 的名稱。
     * 
     * @param arguments
     * @see org.eclipse.swt.browser.BrowserFunction#function(java.lang.Object[])
     */
    @Override
    public Object function(Object[] arguments) {
        String bundle = (String) arguments[0];
        String bundleVersion = (String) arguments[1];
        String handlerName = (String) arguments[2];

        Object result = "";
        try {
            if ("".equals(bundleVersion)) {
                Bundle b = Platform.getBundle(bundle);
                AbstractAquariusJavaHandler handler = (AbstractAquariusJavaHandler) b
                        .loadClass(handlerName).newInstance();
                handler.setDao(dao);
                result = handler.execute(arguments);
            } else {
                Bundle[] bs = Platform.getBundles(bundle, bundleVersion);
                if (bs != null) {
                    Bundle b = bs[0];
                    if (!bundleVersion.equals(b.getVersion().toString())) {
                        // TODO 版本不合，報錯

                    } else {
                        AbstractAquariusJavaHandler handler = (AbstractAquariusJavaHandler) b
                                .loadClass(handlerName).newInstance();
                        handler.setDao(dao);
                        result = handler.execute(arguments);
                    }
                } else {
                    // TODO 找不到

                }
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}
