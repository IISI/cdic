package tw.com.citi.cdic.batch.dao.jdbc;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.T01Dao;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/11/12
 */
public class T01DaoImpl extends SimpleJdbcDaoSupport implements T01Dao {

    public T01 findByCode(String code, String tableName) {
        String sql = "SELECT * FROM " + tableName + " WHERE CODE=?";
        return getSimpleJdbcTemplate().queryForObject(sql, T01.class, code);
    }

}
