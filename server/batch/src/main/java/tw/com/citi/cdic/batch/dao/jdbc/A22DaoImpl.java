package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A22Mapper;
import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.model.A22;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A22DaoImpl extends SimpleJdbcDaoSupport implements A22Dao {

    @Override
    public A22 findBySrNo(String srNo, String tableName) {
        List<A22> results = super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE LEFT(TDSRNO, 10) = ?", new A22Mapper(), srNo);
        if (results != null && results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public A22 findByCustomerIdAndSrNo(String customerId, String srNo,
            String tableName) {
        List<A22> results = super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE TDCUSTID = ? AND TDSRNO = ?", new A22Mapper(), customerId, srNo);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public List<A22> findByCustomerId(String customerId, String tableName) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE TDCUSTID = ?", new A22Mapper(), customerId);
    }

    @Override
    public List<A22> findByCustomerIdAndHasAmountIntPayable(String customerId,
            String tableName) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE TDCUSTID = ? AND (TDAMT > 0 OR TDINTPAYABLE > 0)", new A22Mapper(), customerId);
    }

}
