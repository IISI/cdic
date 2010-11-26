package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A74;

public class A74Mapper implements RowMapper<A74> {

    @Override
    public A74 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A74 a74 = new A74();
        a74.setUnit(rs.getString("UNIT"));
        a74.setBranchNo(rs.getString("BRNO"));
        a74.setCurrencyCode(rs.getString("CURCODE"));
        a74.setRateType(rs.getString("RATETYPE"));
        a74.setType(rs.getString("RTYPE"));
        a74.setPeriod(rs.getString("PERIOD"));
        a74.setLargeMax(rs.getInt("LARGEMAX"));
        a74.setEffectiveDate(rs.getString("EFFDATE"));
        a74.setRate(rs.getDouble("RATE"));
        return a74;
    }

}
