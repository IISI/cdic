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
        List<A22> results = super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE TDSRNO = ?", new A22Mapper(), srNo);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public A22 findByCustomerIdAndSrNo(String customerId, String srNo,
            String tableName) {
        List<A22> results = super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE TDCUSTID = ? AND TDSRNO = ?", new A22Mapper(), customerId, srNo);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public List<A22> findByCustomerId(String customerId, String tableName) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM " + tableName + " WHERE TDCUSTID = ? AND TDAMT > 0", new A22Mapper(), customerId);
    }

}
