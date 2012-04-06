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
        lus.setBranchCode(rs.getString("BRANCH_CODE"));
        lus.setBranchName(rs.getString("BRANCH_NAME"));
        lus.setCurrCode(rs.getString("CURR_CODE"));
        lus.setClosedBy(rs.getString("CLOSED_BY"));
        lus.setbCode(rs.getString("B_CODE"));
        lus.setLegal_adr(rs.getString("LEGAL_ADR"));
        lus.setTelNo1(rs.getString("TEL_NO_1"));
        lus.setTelNo2(rs.getString("TEL_NO_2"));
        lus.setObuDbu(rs.getString("OBU_DBU"));
        return lus;
    }

}
