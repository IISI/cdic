package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.model.A23;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A23DaoImpl extends SimpleJdbcDaoSupport implements A23Dao {

    @Override
    public A23 findBySrNo(String srno) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public A23 findByCustomerIdAndSrNo(String customerId, String srNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<A23> findByCustomerIdAndJointCode(String customerId,
            String jointCode) {
        // TODO Auto-generated method stub
        return null;
    }

}
