package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A21;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/19
 */
public class A21Mapper implements RowMapper<A21> {

    @Override
    public A21 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A21 a21 = new A21();
        a21.setUnit(rs.getString("PBUNIT"));
        a21.setBranchNo(rs.getString("PBBRNO"));
        a21.setSrNo(rs.getString("PBSRNO"));
        a21.setApNo(rs.getString("PBAPNO"));
        a21.setCharCode(rs.getString("PBCHARCODE"));
        a21.setStatus(rs.getString("PBSTATUS"));
        a21.setCustomerId(rs.getString("PBCUSTID"));
        a21.setCustomerIdNo(rs.getString("CUSTIDNO"));
        a21.setCustomerType(rs.getString("PBCUSTTYPE"));
        a21.setOpenDate(rs.getDate("PBOPENDATE"));
        a21.setCurrencyCode(rs.getString("PBCURCODE"));
        a21.setAccountBalance(rs.getDouble("PBACTBAL"));
        a21.setBalance(rs.getDouble("PBBAL"));
        a21.setStopPayAmount(rs.getDouble("PBSTOPPAYAMT"));
        a21.setCardAmount(rs.getDouble("PBCARDAMT"));
        a21.setGsAccountCode(rs.getString("PBGSACTCODE"));
        a21.setJointCode(rs.getString("PBJOINTCODE"));
        a21.setRateType(rs.getString("PBRATETYPE"));
        a21.setIntRate(rs.getDouble("PBINTRATE"));
        a21.setIntPayable(rs.getDouble("PBINTPAYABLE"));
        a21.setOverdrawStatus(rs.getString("PBOVRSTATUS"));
        a21.setTaxCode(rs.getString("PBTAXCODE"));
        a21.setGrossInt(rs.getDouble("PBGROSSINT"));
        a21.setGrossTax(rs.getDouble("PBGROSSTAX"));
        a21.setLastTxDate(rs.getDate("PBLASTTXDATE"));
        return a21;
    }

}
