package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Customer;

/**
 * Mapping Bus database 的資料成 {@link tw.com.citi.cdic.batch.model.Customer} 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class BusCustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setUnit(rs.getString("CUSTUNIT"));
        customer.setBranchNo(rs.getString("CUSTBRNO"));
        customer.setId(rs.getString("CUSTID"));
        customer.setIdNo(rs.getString("CUSTIDNO"));
        customer.setHeadId(rs.getString("CUSTHEADID"));
        customer.setCName(rs.getString("CUSTCNAME"));
        customer.setBirthDate(rs.getString("CUSTBIRDATE"));
        customer.setCeoCode(rs.getString("CUSTCEOCODE"));
        customer.setCeoName(rs.getString("CUSTCEONAME"));
        customer.setStatusCode(rs.getString("CUSTSTACODE"));
        customer.setBusinessCode(rs.getString("CUSTBUSCODE"));
        customer.setCreateDate(rs.getString("CUSTCRTDATE"));
        customer.setOriginalAddress(rs.getString("CUSTOADDRESS"));
        customer.setAddress(rs.getString("CUSTADDRESS"));
        customer.setTel1(rs.getString("CUSTTEL1"));
        customer.setTel2(rs.getString("CUSTTEL2"));
        customer.setEmail(rs.getString("CUSTEMAILADD"));
        return customer;
    }

}
