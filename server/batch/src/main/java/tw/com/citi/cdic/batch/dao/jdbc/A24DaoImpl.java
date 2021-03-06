package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A24Mapper;
import tw.com.citi.cdic.batch.dao.A24Dao;
import tw.com.citi.cdic.batch.model.A24;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/4
 */
public class A24DaoImpl extends SimpleJdbcDaoSupport implements A24Dao {

    @Override
    public List<A24> findByCustomerId(String customerId) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM A24 WHERE STCUSTID = ?", new A24Mapper(), customerId);
    }

}
