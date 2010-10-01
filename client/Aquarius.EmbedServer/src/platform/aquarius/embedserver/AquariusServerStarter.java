package platform.aquarius.embedserver;

import java.net.URL;

import org.apache.wicket.protocol.http.WicketFilter;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.beans.factory.InitializingBean;

public class AquariusServerStarter implements InitializingBean {

    private Server server;

    public AquariusServerStarter() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        server = new Server(8080);

        final ServletContextHandler servletCtx_handler = new ServletContextHandler(
                ServletContextHandler.SESSIONS);

        servletCtx_handler.setContextPath("/app");

        // -- 開始 Wicket Filter設定
        servletCtx_handler.addServlet(
                "platform.aquarius.embedserver.DefaultServlet", "/*");
        FilterHolder filter_holder = new FilterHolder(WicketFilter.class);
        filter_holder.setInitParameter("applicationClassName",
                AquariusWicketApplication.class.getCanonicalName());
        filter_holder.setName("WebApplication");
        servletCtx_handler.addFilter(filter_holder, "/*", 0);
        // -- 結束

        // -- 開始共用Web Resource設定
        ResourceHandler rhandler = new ResourceHandler();
        URL url = Platform.getBundle("platform.aquarius.resources").getEntry(
                "/");
        rhandler.setBaseResource(new OSGiBundleResource(url, url
                .openConnection()));
        // resource_handler.setDirectoriesListed(true);
        // resource_handler.setResourceBase(rsPath);
        // -- 結束

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { rhandler, // resource_handler,
                servletCtx_handler });
        server.setHandler(handlers);
        QueuedThreadPool qtp = new QueuedThreadPool();
        qtp.setMinThreads(5);
        qtp.setMaxThreads(50);

        server.setThreadPool(qtp);

        server.start();
        // server.join();
    }
}
