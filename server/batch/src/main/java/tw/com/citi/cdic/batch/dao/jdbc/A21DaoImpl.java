package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.model.A21;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A21DaoImpl extends SimpleJdbcDaoSupport implements A21Dao {

    @Override
    public A21 findBySrNo(String srNo, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public A21 findByCustomerIdAndSrNo(String customerId, String srNo,
            String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A21> findByCustomerIdAndJointCode(String customerId,
            String jointCode, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A21> findByCustomerId(String customerId, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

}
