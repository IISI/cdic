package platform.aquarius.embedserver;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.HttpSessionStore;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.protocol.http.request.CryptedUrlWebRequestCodingStrategy;
import org.apache.wicket.protocol.http.request.WebRequestCodingStrategy;
import org.apache.wicket.request.IRequestCodingStrategy;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.request.target.coding.MixedParamUrlCodingStrategy;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;

import platform.aquarius.embedserver.pages.DefaultPage;

public class AquariusWicketApplication extends WebApplication {

    boolean firstTime = true;
    Object obj = new Object();

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.protocol.http.WebApplication#newSessionStore()
     */
    @Override
    protected ISessionStore newSessionStore() {
        return new HttpSessionStore(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.protocol.http.WebApplication#init()
     */
    @Override
    protected void init() {
        super.init();
        getApplicationSettings().setClassResolver(
                new AquariusWicketOSGiClassResolver());

        getMarkupSettings().setStripWicketTags(true);
        getMarkupSettings().setCompressWhitespace(true);
        getPageSettings().setAutomaticMultiWindowSupport(false);

        // QueryStringAnnotatedMountScanner mounter = new
        // QueryStringAnnotatedMountScanner();
        // mounter.scanPackage("platform.aquarius.embedserver").mount(this);

        MixedParamUrlCodingStrategy mixedParamUrlCodingStrategy = new MixedParamUrlCodingStrategy(
                "/dispatch", DispatchPage.class, new String[] { "pageName",
                        "bundleId", "bundleVersion" });
        mount(mixedParamUrlCodingStrategy);
    }

    @Override
    public RequestCycle newRequestCycle(final Request request,
            final Response response) {

        synchronized (obj) {
            if (firstTime) {
                ApplicationContext ctx = Activator
                        .getSpringContext(Activator.PLUGIN_ID);
                addComponentInstantiationListener(new SpringComponentInjector(
                        this, ctx, true));
                firstTime = false;
            }
        }

        return new AquariusRequestCycle(this, (WebRequest) request,
                (WebResponse) response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        // IConfigurationElement[] config = Platform.getExtensionRegistry()
        // .getConfigurationElementsFor(
        // EmbedServerConstant.SERVLET_PLUGIN_POINT_ID);
        //
        // try {
        // return (Class<? extends Page>) config[0].createExecutableExtension(
        // "class").getClass();
        // } catch (CoreException e) {
        // LoggerFactory.getLogger(this.getClass()).debug(e.getMessage(), e);
        // }

        return DefaultPage.class;
    }

    /**
     * Gets the validate fail page.
     * 
     * @return the validate fail page
     */
    public Class<? extends Page> getValidateFailPage() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.wicket.protocol.http.WebApplication#newRequestCycleProcessor()
     */
    @Override
    protected IRequestCycleProcessor newRequestCycleProcessor() {
        return new WebRequestCycleProcessor() {
            /*
             * (non-Javadoc)
             * 
             * @seeorg.apache.wicket.protocol.http.WebRequestCycleProcessor#
             * newRequestCodingStrategy()
             */
            protected IRequestCodingStrategy newRequestCodingStrategy() {
                return new CryptedUrlWebRequestCodingStrategy(
                        new WebRequestCodingStrategy());
            }
        };
    }
}
