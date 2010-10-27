package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A73;

public class A73Mapper implements RowMapper<A73> {

    @Override
    public A73 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A73 a73 = new A73();
        a73.setUnit(rs.getString("UNIT"));
        a73.setBranchNo(rs.getString("BRANCH_NO"));
        a73.setSrNo(rs.getString("SR_NO"));
        a73.setDepositReceiptNo(rs.getString("DEPOSIT_RECEIPT_NO"));
        a73.setTxnDate(rs.getDate("TXN_DATE"));
        a73.setSerNo(rs.getString("SER_NO"));
        a73.setTxnReason(rs.getString("TXN_REASON"));
        a73.setCurrencyCode(rs.getString("CURRENCY_CODE"));
        a73.setHoldAmt(rs.getDouble("HOLD_AMT"));
        a73.setMemo(rs.getString("MEMO"));
        return a73;
    }

}
