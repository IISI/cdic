package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A21Mapper;
import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.model.A21;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A21DaoImpl extends SimpleJdbcDaoSupport implements A21Dao {

    @Override
    public A21 findBySrNo(String srNo, String tableName) {
        List<A21> results = super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE LEFT(PBSRNO, 10) = ?", new A21Mapper(), srNo);
        if (results != null && results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public A21 findByCustomerIdAndSrNo(String customerId, String srNo,
            String tableName) {
        List<A21> results = super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE PBCUSTID = ? and PBSRNO = ?", new A21Mapper(), customerId, srNo);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public List<A21> findByCustomerId(String customerId, String tableName) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE PBCUSTID = ? AND PBACTBAL > 0", new A21Mapper(), customerId);
    }

}
