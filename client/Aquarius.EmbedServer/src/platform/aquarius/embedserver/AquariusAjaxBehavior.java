/**
 * CapAjaxBehavior.java
 * 
 * 2009/9/23 下午 01:10:06
 * 
 * Copyright (c) 2009 International Integrated System, Inc.
 * 11F, No.133, Sec.4, Minsheng E. Rd., Taipei, 10574, Taiwan, R.O.C.
 * All Rights Reserved.
 *
 * Licensed Materials - Property of International Integrated System, Inc.
 *
 * This software is confidential and proprietary information of
 * International Integrated System, Inc. ("Confidential Information").
 */
package platform.aquarius.embedserver;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.request.target.basic.StringRequestTarget;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.pages.AbstractAquariusPage;

/**
 * 
 * @author Tony
 * 
 */
public class AquariusAjaxBehavior extends AbstractAjaxBehavior {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(AquariusAjaxBehavior.class);
    public static final String AJAX_HANDLER_BUNDLE_KEY = "_ab";
    public static final String AJAX_HANDLER_BUNDLE_DEFAULT = "Aquarius.AjaxHandler";
    public static final String AJAX_HANDLER_BUNDLE_VERSION_KEY = "_v";
    public static final String AJAX_HANDLER_KEY = "_ah";
    public static final String AJAX_DEFAULT_HANDLER_NAME = "defaultJsonAjaxHandler";
    private static final String EMPTY_STR = "";

    private AbstractAquariusPage owner;

    public AquariusAjaxBehavior(AbstractAquariusPage owner) {
        this.owner = owner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.wicket.behavior.AbstractAjaxBehavior#renderHead(org.apache
     * .wicket.markup.html.IHeaderResponse)
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        /*
         * this.getCallbackUrl(false), false代表非最新的page也可以用這個call back
         */
        response.renderJavascript(
                "var __ajaxHandler=window.location.pathname+'"
                        + getCallbackUrl(false) + "';",
                "Inserted-by-AquariusAjaxBehavior-"
                        + System.currentTimeMillis());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.wicket.behavior.AbstractAjaxBehavior#getStatelessHint(org.
     * apache.wicket.Component)
     */
    @Override
    public boolean getStatelessHint(Component component) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.behavior.IBehaviorListener#onRequest()
     */
    public void onRequest() {
        final RequestCycle requestCycle = RequestCycle.get();
        final PageParameters params = new PageParameters(requestCycle
                .getRequest().getParameterMap());

        logger.debug(requestCycle.getSession().getId() + ", ajaxRequestParam: "
                + params);

        String ajaxHandlerBundle = params.getString(AJAX_HANDLER_BUNDLE_KEY,
                AJAX_HANDLER_BUNDLE_DEFAULT);
        String ajaxHandlerBundleVersion = params.getString(
                AJAX_HANDLER_BUNDLE_VERSION_KEY, "");
        String ajaxHandlerName = params.getString(AJAX_HANDLER_KEY,
                AJAX_DEFAULT_HANDLER_NAME);

        AbstractAquariusPage page = (AbstractAquariusPage) getComponent();

        Object result = EMPTY_STR;
        try {
            // 執行Owner Page的processRequest方法, 目的是ajax call發生時可以refresh session
            // timeout
            owner.processRequest(true);

            // 執行 Ajax
            if ("".equals(ajaxHandlerBundleVersion)) {
                // 找最新的
                Bundle b = Platform.getBundle(ajaxHandlerBundle);
                Version ver = b.getVersion();

                logger.debug("Execute Ajax, BUNDLE_NAME: " + b.getBundleId()
                        + ", VERSION: " + ver + ", HANDLER_NAME: "
                        + ajaxHandlerName);

                IAquariusAjaxHandler handler = (IAquariusAjaxHandler) b
                        .loadClass(ajaxHandlerName).newInstance();
                handler.setDao(page.getDao());
                result = handler.execute(params);
            } else {
                // 用指定的, 回傳array應是由小version至大version
                Bundle[] bs = Platform.getBundles(ajaxHandlerBundle,
                        ajaxHandlerBundleVersion);

                if (bs != null) {
                    Bundle b = bs[0];

                    logger.debug("Execute Ajax, BUNDLE_NAME: "
                            + b.getBundleId() + ", VERSION: " + b.getVersion()
                            + ", HANDLER_NAME: " + ajaxHandlerName);

                    if (!b.getVersion().toString()
                            .equals(ajaxHandlerBundleVersion)) {
                        // 版本不合, 報錯
                        // TODO
                    } else {
                        IAquariusAjaxHandler handler = (IAquariusAjaxHandler) b
                                .loadClass(ajaxHandlerName).newInstance();
                        handler.setDao(page.getDao());
                        result = handler.execute(params);
                    }
                } else {
                    // 找不到
                    // TODO
                }
            }

        } catch (Exception e) {
            // TODO
            // errorMsg
            logger.error(e.getMessage(), e);
            
            // Add by Chih-Liang Chang
            ((WebResponse) requestCycle.getResponse()).getHttpServletResponse().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result = "{\"type\":\"" + e.getClass().getName() + "\",\"message\":\"" + e.getMessage() + "\"}";
        }

        // 判斷是否為檔案,否則用String輸出
        if (!(params.containsKey("useDefault") && !params
                .getBoolean("useDefault"))) {

            // TODO

            requestCycle.setRequestTarget(new StringRequestTarget("text/plain",
                    "utf-8", result.toString()));
        }
    }
}
