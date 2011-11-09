package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A44;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/30
 */
public class A44Mapper implements RowMapper<A44> {

    @Override
    public A44 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A44 item = new A44();
        item.setUnit(rs.getString("GULNUNIT"));
        item.setBrNo(rs.getString("GULNBRNO"));
        item.setSrNo(rs.getString("GULNSRNO"));
        item.setSrSubNo(rs.getString("GULNSRSUBNO"));
        item.setCustId(rs.getString("GULNCUSTID"));
        item.setArantId(rs.getString("GUARANTID"));
        item.setArantName(rs.getString("GUARANTNAME"));
        item.setCharCode(rs.getString("GUCHARCODE"));
        item.setRelCode(rs.getString("GURELCODE"));
        item.setCurCode(rs.getString("GUCURCODE"));
        item.setAmt(rs.getDouble("GUAMT"));
        item.setEffDate(rs.getString("GUEFFDATE"));
        return item;
    }

}
