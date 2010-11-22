package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A22;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/19
 */
public class A22Mapper implements RowMapper<A22> {

    @Override
    public A22 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A22 a22 = new A22();
        a22.setUnit(rs.getString("TDUNIT"));
        a22.setBranchNo(rs.getString("TDBRNO"));
        a22.setSrNo(rs.getString("TDSRNO"));
        a22.setApNo(rs.getString("TDAPNO"));
        a22.setCharCode(rs.getString("TDCHARCODE"));
        a22.setStatus(rs.getString("TDSTATUS"));
        a22.setCustomerId(rs.getString("TDCUSTID"));
        a22.setCustomerIdNo(rs.getString("TDCUSTIDNO"));
        a22.setCustomerType(rs.getString("TDCUSTTYPE")); 
        a22.setSlipNo(rs.getString("TDSLIPNO"));
        a22.setCurrencyCode(rs.getString("TDCURCODE"));
        a22.setAmount(rs.getDouble("TDAMT"));
        a22.setStopPayAmount(rs.getDouble("TDSTOPPAYAMT"));
        a22.setBeginDate(rs.getString("TDBGNDATE"));
        a22.setDueDate(rs.getString("TDDUEDATE"));
        a22.setRateType(rs.getString("TDRATETYPE"));
        a22.setPeriod(rs.getString("TDPERIOD"));
        a22.setIntType(rs.getString("TDINTTYPE"));
        a22.setNameCode(rs.getString("TDNAMECODE"));
        a22.setIntRate(rs.getDouble("TDINTRATE"));
        a22.setIntPayCode(rs.getString("TDINTPAYCODE"));
        a22.setAutoPrim(rs.getString("TDAUTOPRIM"));
        a22.setAutoIntNo(rs.getString("TDAUTOINTNO"));
        a22.setIssueDate(rs.getString("TDISUEDATE"));
        a22.setReIssueDate(rs.getString("TDREISUEDATE"));
        a22.setGsAccountCode(rs.getString("TDGSACTCODE"));
        a22.setJointCode(rs.getString("TDJOINTCODE"));
        a22.setSdCase(rs.getString("TDSDCASE"));
        a22.setIntEndDate(rs.getString("TDINTEDATE"));
        a22.setIntPay(rs.getDouble("TDINTPAY"));
        a22.setIntPayable(rs.getDouble("TDINTPAYABLE"));
        a22.setViolateAmount(rs.getDouble("TDVIOLATEAMT"));
        a22.setPgKind(rs.getString("TDPGKIND"));
        a22.setPgAmount(rs.getDouble("TDPGAMT"));
        a22.setPgSetDate(rs.getString("TDPGSETDATE"));
        a22.setTaxCode(rs.getString("TDTAXCODE"));
        a22.setGrossInt(rs.getDouble("TDGROSSINT"));
        a22.setGrossTax(rs.getDouble("TDGROSSTAX"));
        a22.setLastTxDate(rs.getString("TDLASTTXDATE"));
        return a22;
    }

}
