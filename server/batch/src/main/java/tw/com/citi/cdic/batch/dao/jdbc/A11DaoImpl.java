package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A11Mapper;
import tw.com.citi.cdic.batch.dao.A11Dao;
import tw.com.citi.cdic.batch.model.A11;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/20
 */
public class A11DaoImpl extends SimpleJdbcDaoSupport implements A11Dao {

    @Override
    public A11 findByNationalIdAndTitle(String nationalId, String title) {
        List<A11> results = super.getSimpleJdbcTemplate().query("SELECT * FROM A11 WHERE CUSTHEADID = ? AND CUSTCNAME = ? AND LEFT(CUSTID, " + nationalId.length() + ") = ?", new A11Mapper(), nationalId, title, nationalId + "%");
        A11 a11 = DataAccessUtils.singleResult(results);
        return a11;
    }

}
