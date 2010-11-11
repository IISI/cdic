package platform.aquarius.embedserver;

import org.apache.wicket.protocol.http.WebRequest;

import platform.aquarius.embedserver.jdbc.IDao;

public abstract class AquariusAjaxDaoHandler implements IAquariusAjaxHandler {
    private IDao dao;
    private WebRequest request;

    public AquariusAjaxDaoHandler() {
        super();
    }

    @Override
    public IDao getDao() {
        return this.dao;
    }

    @Override
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    public void setRequest(WebRequest request) {
        this.request = request;
    }

    public WebRequest getRequest() {
        return request;
    }

}