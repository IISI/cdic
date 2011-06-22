package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A24;

public class A24SumMapper implements RowMapper<A24> {

    @Override
    public A24 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A24 a24 = new A24();
        a24.setCustomerId(rs.getString("STCUSTID"));
        a24.setBalance(rs.getDouble("STBAL"));
        a24.setIntPayable(rs.getDouble("STINTPAYABLE"));
        return a24;
    }

}
