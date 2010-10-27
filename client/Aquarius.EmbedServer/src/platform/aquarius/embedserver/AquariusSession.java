package platform.aquarius.embedserver;

import java.util.Locale;

import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.context.i18n.LocaleContextHolder;

public class AquariusSession extends WebSession {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The user id. */
    private String userId;

    /** The role id. */
    private String roleId;

    /** The login. */
    private boolean login;

    /** The locale. */
    private Locale locale;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.Session#setLocale(java.util.Locale)
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        LocaleContextHolder.setLocale(this.locale);
    }

    /**
     * Instantiates a new cap session.
     * 
     * @param request
     *            the request
     */
    public AquariusSession(Request request) {
        super(request);
        /*
         * Create Http Servlet Session
         */
        getSessionStore().getSessionId(RequestCycle.get().getRequest(), true);

        /*
         * Set Default Locale
         */
        setLocale(Locale.TAIWAN);
        LocaleContextHolder.setLocale(Locale.TAIWAN);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.wicket.Session#getLocale()
     */
    @Override
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Gets the user id.
     * 
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     * 
     * @param userId
     *            the new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the role id.
     * 
     * @return the role id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the role id.
     * 
     * @param roleId
     *            the new role id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * Checks if is login.
     * 
     * @return true, if is login
     */
    public boolean isLogin() {
        return login;
    }

    /**
     * Sets the login.
     * 
     * @param login
     *            the new login
     */
    public void setLogin(boolean login) {
        this.login = login;
    }
}
