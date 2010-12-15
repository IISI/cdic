package tw.com.citi.cdic.batch.dao.jdbc;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.FMBCDWN4Dao;
import tw.com.citi.cdic.batch.model.FMBCDWN4;

/**
 * @author Lancelot
 * @since 2010/11/12
 */
public class FMBCDWN4DaoImpl extends SimpleJdbcDaoSupport implements FMBCDWN4Dao {

    public FMBCDWN4 findByAcctAndIBCode(String acct, String IBCode) {
        String sql = "SELECT * FROM T02 WHERE acct=?";
        FMBCDWN4 data = null;
        try {
            data = getSimpleJdbcTemplate().queryForObject(sql, FMBCDWN4.class, new Object[] { acct });
        } catch (Exception e) {
            // do nothing
        }
        return data;
    }

}
