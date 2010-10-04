package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A11;

/**
 * Mapping Bus database 的資料成 {@link tw.com.citi.cdic.batch.model.A11} 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class BusA11Mapper implements RowMapper<A11> {

    @Override
    public A11 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A11 a11 = new A11();
        a11.setUnit(rs.getString("CUSTUNIT"));
        a11.setBranchNo(rs.getString("CUSTBRNO"));
        a11.setId(rs.getString("CUSTID"));
        a11.setIdNo(rs.getString("CUSTIDNO"));
        a11.setHeadId(rs.getString("CUSTHEADID"));
        a11.setCName(rs.getString("CUSTCNAME"));
        a11.setBirthDate(rs.getString("CUSTBIRDATE"));
        a11.setCeoCode(rs.getString("CUSTCEOCODE"));
        a11.setCeoName(rs.getString("CUSTCEONAME"));
        a11.setStatusCode(rs.getString("CUSTSTACODE"));
        a11.setBusinessCode(rs.getString("CUSTBUSCODE"));
        a11.setCreateDate(rs.getString("CUSTCRTDATE"));
        a11.setOriginalAddress(rs.getString("CUSTOADDRESS"));
        a11.setAddress(rs.getString("CUSTADDRESS"));
        a11.setTel1(rs.getString("CUSTTEL1"));
        a11.setTel2(rs.getString("CUSTTEL2"));
        a11.setEmail(rs.getString("CUSTEMAILADD"));
        return a11;
    }

}
