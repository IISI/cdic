package platform.aquarius.embedserver.pages;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.aquarius.embedserver.AquariusAjaxBehavior;
import platform.aquarius.embedserver.AquariusWicketApplication;
import platform.aquarius.embedserver.jdbc.IDao;

/**
 * @author Tony
 * 
 */
public abstract class AbstractAquariusPage extends WebPage {
    /** The Constant PRE_VALIDATE_MESSAGE. */
    public static final String PRE_VALIDATE_MESSAGE = "PRE_VALIDATE_MESSAGE";

    /** Behavior to process all client ajax call. */
    AquariusAjaxBehavior ajax = new AquariusAjaxBehavior(this);

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory
            .getLogger(AbstractAquariusPage.class);

    @SpringBean
    private IDao dao;

    /**
     * Log debug.
     * 
     * @param message
     *            the message
     * @param t
     *            the t
     */
    public void logDebug(String message, Throwable t) {
        logger.debug("(" + getSession().getId() + ") " + message, t);
    }

    /**
     * Log error.
     * 
     * @param message
     *            the message
     * @param t
     *            the t
     */
    public void logError(String message, Throwable t) {
        logger.error("(" + getSession().getId() + ") " + message, t);
    }

    /**
     * Constructor
     */
    public AbstractAquariusPage() {
        super();
        execute();
    }

    /**
     * Constructor
     * 
     * @param parameters
     */
    public AbstractAquariusPage(PageParameters parameters) {
        // Session Revalid
        super(parameters);
        execute();
    }

    private void execute() {
        // -- fix ajax call will rerender page
        add(ajax);

        try {
            processRequest(false);
        } catch (Exception e) {// nouse
            e.printStackTrace();
        }
        // -- end fix
    }

    /**
     * Page 及 ajax Call共用的 request process流程
     * 
     * @param isAjax
     * @throws Exception
     */
    public void processRequest(boolean isAjax) throws Exception {
        PageParameters para = new PageParameters();
        AquariusWicketApplication app = (AquariusWicketApplication) getApplication();

        try {
            // 1. 先驗証
            // preValidation(user);

            // 1.1 如果是ajax call就不再render以下的page
            if (isAjax)
                return;

            // 2. Render共用部分

        } catch (Exception e) {
            // 如果是ajax call則exception交由CapAjaxBehavior處理
            if (isAjax)
                throw e;

            logError(e.getMessage(), e);
            para.add(PRE_VALIDATE_MESSAGE, e.getMessage());
            setResponsePage(app.getValidateFailPage(), para);
        }

        // 3. 執行By Page的處理

    }

    public IDao getDao() {
        return dao;
    }

}
