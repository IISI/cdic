package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A61;

public class A61Mapper implements RowMapper<A61> {

    @Override
    public A61 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A61 a61 = new A61();
        a61.setUnit("021");
        a61.setBranchNo("0000");
        a61.setDate(rs.getString("DATE"));
        a61.setCustId(rs.getString("JOINT_ID"));
        a61.setAcctBalance(rs.getDouble("TEMP7"));
        a61.setAcctInt(rs.getDouble("TEMP8"));
        a61.setNoAcctBalance(rs.getDouble("TEMP9"));
        a61.setNoAcctInt(rs.getDouble("TEMP10"));
        a61.setUnDepBalance(rs.getDouble("TEMP11"));
        a61.setUnDepInt(rs.getDouble("TEMP12"));
        a61.setAcctBalanceEx(rs.getDouble("TEMP13"));
        a61.setAcctIntEx(rs.getDouble("TEMP14"));
        a61.setNoAcctBalanceEx(rs.getDouble("TEMP15"));
        a61.setNoAcctIntEx(rs.getDouble("TEMP16"));
        a61.setUnDepBalanceEx(rs.getDouble("TEMP17"));
        a61.setUnDepIntEx(rs.getDouble("TEMP18"));
        a61.setObuDepBalance(rs.getDouble("TEMP19"));
        a61.setObuDepInt(rs.getDouble("TEMP20"));
        return a61;
    }

}
