package tw.com.citi.cdic.batch.dao.jdbc;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.A11Dao;
import tw.com.citi.cdic.batch.model.A11;

public class A11DaoImpl extends SimpleJdbcDaoSupport implements A11Dao {

    @Override
    public A11 findById(String id) {
        String sql = "SELECT * FROM A11 WHERE ID=?";
        return getSimpleJdbcTemplate().queryForObject(sql, A11.class, id);
    }

}
