package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A23Mapper;
import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.model.A23;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A23DaoImpl extends SimpleJdbcDaoSupport implements A23Dao {

    @Override
    public A23 findBySrNo(String srNo) {
        List<A23> results = super.getSimpleJdbcTemplate().query("SELECT * FROM A23 WHERE LEFT(CKSRNO, 10) = ?", new A23Mapper(), srNo);
        if (results != null && results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public A23 findByCustomerIdAndSrNo(String customerId, String srNo) {
        List<A23> results = super.getSimpleJdbcTemplate().query("SELECT * FROM A23 WHERE CKCUSTID = ? AND CKSRNO = ?", new A23Mapper(), customerId, srNo);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public List<A23> findByCustomerId(String customerId) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM A23 WHERE CKCUSTID = ?", new A23Mapper(), customerId);
    }

    @Override
    public List<A23> findByCustomerIdAndHasAcctBal(String customerId) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM A23 WHERE CKCUSTID = ? AND CKACTBAL > 0", new A23Mapper(), customerId);
    }

}
