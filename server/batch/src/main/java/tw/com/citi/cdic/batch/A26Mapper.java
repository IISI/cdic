package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A26;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/31
 */
public class A26Mapper implements RowMapper<A26> {

    @Override
    public A26 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A26 a26 = new A26();
        a26.setUnit(rs.getString("OTUNIT"));
        a26.setBranchNo(rs.getString("OTBRNO"));
        a26.setApNo(rs.getString("OTAPNO"));
        a26.setSrNo(rs.getString("OTSRNO"));
        a26.setCustomerId(rs.getString("OTCUSTID"));
        a26.setCustomerIdNo(rs.getString("OTCUSTIDNO"));
        a26.setCheckNo(rs.getString("OTCHECKNO"));
        a26.setCurrencyCode(rs.getString("OTCURCODE"));
        a26.setPaySav(rs.getDouble("OTPAYSAV"));
        a26.setIntPayable(rs.getString("OTINTPAYABLE"));
        a26.setIntPayMemo(rs.getString("OTINTPAYMEMO"));
        a26.setCompany(rs.getString("OTCOMPCODE"));
        a26.setRc(rs.getString("OTRCCODE"));
        a26.setRefNo(rs.getString("OTREFNO"));
        return a26;
    }

}
