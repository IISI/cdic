package tw.com.citi.cdic.batch.dao.jdbc;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.A11ADao;
import tw.com.citi.cdic.batch.model.A11;

public class A11ADaoImpl extends SimpleJdbcDaoSupport implements A11ADao {

    @Override
    public A11 findById(String id) {
        String sql = "SELECT * FROM A11A WHERE ID=?";
        return getSimpleJdbcTemplate().queryForObject(sql, A11.class, id);
    }

    @Override
    public A11 findByAccountNo(String accountNo) {
        String sql = "SELECT * FROM A11A WHERE ACCOUNTNO=?";
        return getSimpleJdbcTemplate().queryForObject(sql, A11.class, accountNo);
    }

}
