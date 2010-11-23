package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A24;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/17
 */
public class A24Mapper implements RowMapper<A24> {

    @Override
    public A24 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A24 a24 = new A24();
        a24.setUnit(rs.getString("STUNIT"));
        a24.setBranchNo(rs.getString("STBRNO"));
        a24.setControlSrNo(rs.getString("STCTLSRNO"));
        a24.setSrNo(rs.getString("STSRNO"));
        a24.setApNo(rs.getString("STAPNO"));
        a24.setCharCode(rs.getString("STCHARCODE"));
        a24.setCustomerId(rs.getString("STCUSTID"));
        a24.setCustomerIdNo(rs.getString("CKCUSTIDNO"));
        a24.setCustomerName(rs.getString("STCUSTNAME"));
        a24.setCustomerBusinessCode(rs.getString("STCUSTBUSCODE"));
        a24.setCustomerType(rs.getString("STCUSTTYPE"));
        a24.setCurrencyCode(rs.getString("STCURCODE"));
        a24.setBalance(rs.getDouble("STBAL"));
        a24.setRateType(rs.getString("STRATETYPE"));
        a24.setIntRate(rs.getDouble("STINTRATE"));
        a24.setIntPayable(rs.getDouble("STINTPAYABLE"));
        a24.setTaxCode(rs.getString("STTAXCODE"));
        a24.setGrossInt(rs.getDouble("STGROSSINT"));
        a24.setGrossTax(rs.getDouble("STGROSSTAX"));
        a24.setOriginalAddress(rs.getString("STOADDRESS"));
        a24.setAddress(rs.getString("STADDRESS"));
        a24.setTel1(rs.getString("STTEL1"));
        a24.setTel2(rs.getString("STTEL2"));
        return a24;
    }

}
