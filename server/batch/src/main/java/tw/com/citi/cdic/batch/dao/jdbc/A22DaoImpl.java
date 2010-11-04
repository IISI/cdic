package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.model.A22;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A22DaoImpl extends SimpleJdbcDaoSupport implements A22Dao {

    @Override
    public A22 findBySrNo(String srNo, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public A22 findByCustomerIdAndSrNo(String customerId, String srNo,
            String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A22> findByCustomerIdAndJointCode(String customerId,
            String JointCode, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A22> findByCustomerIdAndJointCodeAndCharCodeAndAmount(
            String customerId, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A22> findByCustomerIdAndJointCodeAndCharCodeOrSdCaseAndAmount(
            String customerId, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A22> findByCustomerId(String customerId, String tableName) {
        // TODO Auto-generated method stub
        return null;
    }

}
