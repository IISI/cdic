package platform.aquarius.embedserver;

import java.io.InputStreamReader;

import org.apache.wicket.PageParameters;
import org.eclipse.core.runtime.Platform;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AquariusAjaxJSHandler extends AquariusAjaxDaoHandler {
    public final static String JS_BUNDLE_ID_KEY = "_jbi";
    public final static String JS_BUNDLE_ID_KEY_DEFAULT = "";
    public final static String JS_BUNDLE_VALUE_KEY = "_jbv";
    public final static String JS_BUNDLE_VALUE_KEY_DEFAULT = "";
    public final static String JS_HANDLER_NAME_KEY = "_jhn";
    public final static String JS_HANDLER_NAME_KEY_DEFAULT = "";
    private static final String EMPTY_STR = "";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object execute(PageParameters params) throws Exception {

        Object result = EMPTY_STR;
        Context cx = Context.enter();

        try {
            Scriptable scope = new ImporterTopLevel(cx);
            cx.evaluateReader(
                    scope,
                    new InputStreamReader(Platform
                            .getBundle("platform.aquarius.resources")
                            .getEntry("/json.js").openStream()), "<jshandler>",
                    0, null);
            Object jsLogger = Context.javaToJS(logger, scope);
            ScriptableObject.putProperty(scope, "logger", jsLogger);

            String jsHandlerBundle = params.getString(JS_BUNDLE_ID_KEY,
                    JS_BUNDLE_ID_KEY_DEFAULT);
            String jsHandlerBundleVersion = params.getString(
                    JS_BUNDLE_VALUE_KEY, JS_BUNDLE_VALUE_KEY_DEFAULT);
            String jsHandlerName = params.getString(JS_HANDLER_NAME_KEY,
                    JS_HANDLER_NAME_KEY_DEFAULT);

            // 執行 JS
            if ("".equals(jsHandlerBundleVersion)) {
                // 找最新的
                Bundle b = Platform.getBundle(jsHandlerBundle);
                Version ver = b.getVersion();

                logger.debug("Execute JS, BUNDLE_NAME: " + b.getBundleId()
                        + ", VERSION: " + ver + ", HANDLER_NAME: "
                        + jsHandlerName);

                cx.evaluateReader(scope,
                        new InputStreamReader(b.getEntry(jsHandlerName)
                                .openStream()), "<jshandler>", 0, null);
                result = scope.get("result", scope);
                logger.debug(result.toString());
            } else {
                // 用指定的, 回傳array應是由小version至大version
                Bundle[] bs = Platform.getBundles(jsHandlerBundle,
                        jsHandlerBundleVersion);

                if (bs != null) {
                    Bundle b = bs[0];

                    logger.debug("Execute Ajax, BUNDLE_NAME: "
                            + b.getBundleId() + ", VERSION: " + b.getVersion()
                            + ", HANDLER_NAME: " + jsHandlerName);

                    if (!b.getVersion().toString()
                            .equals(jsHandlerBundleVersion)) {
                        // 版本不合, 報錯
                        // TODO
                    } else {
                        cx.evaluateReader(scope, new InputStreamReader(b
                                .getEntry(jsHandlerName).openStream()),
                                "<jshandler>", 0, null);
                        result = scope.get("result", scope);
                    }
                } else {
                    // 找不到
                    // TODO
                }
            }
        } finally {
            Context.exit();
        }
        return result;
    }
}
