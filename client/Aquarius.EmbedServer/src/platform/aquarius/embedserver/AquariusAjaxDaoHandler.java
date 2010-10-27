package platform.aquarius.embedserver;

import platform.aquarius.embedserver.jdbc.IDao;

public abstract class AquariusAjaxDaoHandler implements IAquariusAjaxHandler {
    private IDao dao;

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

}