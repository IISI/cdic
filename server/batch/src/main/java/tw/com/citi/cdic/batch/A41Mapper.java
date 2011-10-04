package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A41;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/26
 */
public class A41Mapper implements RowMapper<A41> {

    @Override
    public A41 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A41 a41 = new A41();
        a41.setUnit(rs.getString("UNIT"));
        a41.setBranchNo(rs.getString("BRANCH_NO"));
        a41.setSrNo(rs.getString("SR_NO"));
        a41.setSrSubNo(rs.getString("SR_SUB_NO"));
        a41.setApNo(rs.getString("AP_NO"));
        a41.setCola(rs.getString("COLA"));
        a41.setCharCode(rs.getString("CHAR_CODE"));
        a41.setStatus(rs.getString("STATUS"));
        a41.setCustId(rs.getString("CUST_ID"));
        a41.setGovCode(rs.getString("GOV_CODE"));
        a41.setReplyNo(rs.getString("REPLY_NO"));
        a41.setFirstLoanDate(rs.getString("FIRST_LOAN_DATE"));
        a41.setLoanBeginDate(rs.getString("LOAN_BEGIN_DATE"));
        a41.setDueDate(rs.getString("DUE_DATE"));
        a41.setCurrencyCode(rs.getString("CURRENCY_CODE"));
        a41.setAmt(rs.getDouble("AMT"));
        a41.setCurrentBalance(rs.getDouble("CURRENT_BALANCE"));
        a41.setRateType(rs.getString("RATE_TYPE"));
        a41.setRateAdjustSign(rs.getString("RATE_ADJUST_SIGN"));
        a41.setRateAdjust(rs.getDouble("RATE_ADJUST"));
        a41.setCurrentRate(rs.getDouble("CURRENT_RATE"));
        a41.setIntKind(rs.getString("INT_KIND"));
        a41.setIntCycle(rs.getString("INT_CYCLE"));
        a41.setLastInt(rs.getString("LAST_INT"));
        a41.setNextInt(rs.getString("NEXT_INT"));
        a41.setPayIntType(rs.getString("PAY_INT_TYPE"));
        a41.setPatStart(rs.getString("PAT_START"));
        a41.setPayAcctNo(rs.getString("PAY_ACCT_NO"));
        a41.setIntReceivable(rs.getDouble("INT_RECEIVABLE"));
        a41.setOverCode(rs.getString("OVER_CODE"));
        a41.setOweInt(rs.getDouble("OWE_INT"));
        a41.setPenalty(rs.getDouble("PENALTY"));
        a41.setOweLawFee(rs.getDouble("OWE_LAW_FEE"));
        a41.setTempAmt(rs.getDouble("TEMP_AMT"));
        a41.setEvlRank(rs.getString("EVL_RANK"));
        a41.setOverdueDate(rs.getString("OVERDUE_DATE"));
        a41.setWriteOffDate(rs.getString("WRITEOFF_DATE"));
        a41.setWriteOffAmt(rs.getDouble("WRITEOFF_AMT"));
        return a41;
    }

}
