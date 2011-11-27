package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A61;

public class A24SumMapper implements RowMapper<A61> {

    @Override
    public A61 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A61 a61 = new A61();
        a61.setUnit("021");
        a61.setBranchNo("0000");
        a61.setCustId(rs.getString("STCUSTID"));
        a61.setDate(rs.getString("DATE"));
        a61.setAcctBalance(rs.getDouble("STBAL"));
        a61.setAcctInt(rs.getDouble("STINTPAYABLE"));
        return a61;
    }

}
