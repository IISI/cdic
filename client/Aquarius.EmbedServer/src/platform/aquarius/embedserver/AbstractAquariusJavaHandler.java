package platform.aquarius.embedserver;

import platform.aquarius.embedserver.jdbc.IDao;

public abstract class AbstractAquariusJavaHandler {

    private IDao dao;

    protected abstract Object execute(Object[] args);

    public IDao getDao() {
        return dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

}
