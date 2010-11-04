package platform.aquarius.embedserver;

import org.apache.wicket.PageParameters;
import org.apache.wicket.protocol.http.WebRequest;

import platform.aquarius.embedserver.jdbc.IDao;

public interface IAquariusAjaxHandler {
    public Object execute(PageParameters params) throws Exception;

    public IDao getDao();

    public void setDao(IDao dao);
    
    public void setRequest(WebRequest request);
}
