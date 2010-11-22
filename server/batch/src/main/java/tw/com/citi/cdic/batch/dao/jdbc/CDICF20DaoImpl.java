package tw.com.citi.cdic.batch.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.CDICF20Dao;
import tw.com.citi.cdic.batch.model.CDICF20;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/19
 */
public class CDICF20DaoImpl extends SimpleJdbcDaoSupport implements CDICF20Dao {

    @Override
    public CDICF20 findByCurrencyCode(String currencyCode) {
        List<CDICF20> results = super.getSimpleJdbcTemplate().query("SELECT * FROM CDICF20 WHERE CURRENCY_CODE = ?", new RowMapper<CDICF20>() {

            @Override
            public CDICF20 mapRow(ResultSet rs, int rowNum) throws SQLException {
                CDICF20 f20 = new CDICF20();
                f20.setUnit(rs.getString("UNIT"));
                f20.setBranchNo(rs.getString("BRANCH_NO"));
                f20.setCurrencyCode(rs.getString("CURRENCY_CODE"));
                f20.setRateDate(rs.getString("RATE_DATE"));
                f20.setTransRate(rs.getDouble("TRANS_RATE"));
                return f20;
            }}, currencyCode);
        return DataAccessUtils.singleResult(results);
    }

}
