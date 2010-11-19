package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Lus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/19
 */
public class LusMapper implements RowMapper<Lus> {

    @Override
    public Lus mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lus lus = new Lus();
        lus.setAcctNo(rs.getString("ACCT_NO"));
        lus.setUnino(rs.getString("UNINO"));
        lus.setProdName(rs.getString("PROD_NAME"));
        lus.setBalance(rs.getDouble("BALANCE"));
        lus.setInterest(rs.getDouble("INTEREST"));
        lus.setStatus(rs.getString("STATUS"));
        lus.setName(rs.getString("NAME"));
        lus.setNewBCode(rs.getString("NEW_B_CODE"));
        lus.setPersonType(rs.getString("PERSON_TYPE"));
        lus.setCompany(rs.getString("COMPANY"));
        lus.setCommAdr(rs.getString("COMM_ADR"));
        return lus;
    }

}
