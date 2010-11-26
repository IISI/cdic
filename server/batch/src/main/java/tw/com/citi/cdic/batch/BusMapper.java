package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/17
 */
public class BusMapper implements RowMapper<Bus> {

    @Override
    public Bus mapRow(ResultSet rs, int rowNum) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Bus bus = new Bus();
        bus.setCustNumb(rs.getString("CUST_NUMB"));
        bus.setCustType(rs.getString("CUST_TYPE"));
        bus.setNatnidRegnnumb(rs.getString("NATNID_REGNNUMB"));
        bus.setCustTitlLine1(rs.getString("CUST_TITL_LINE1"));
        bus.setDsctDescL(rs.getString("DSCT_DESC_L"));
        try {
            String birthday = rs.getString("BIRTHDAY");
            Date date = sdf.parse(birthday);
            bus.setBirthday(date);
        } catch (Exception e) {
            bus.setBirthday(null);
        }
        bus.setBranch(rs.getString("BRANCH"));
        try {
            String establish = rs.getString("DATE_ESTB");
            Date date = sdf.parse(establish);
            bus.setDateEstb(date);
        } catch (Exception e) {
            bus.setDateEstb(null);
        }
        bus.setCustStat(rs.getString("CUST_STAT"));
        bus.setBizType(rs.getString("BIZ_TYPE"));
        bus.setCustAssnnatid(rs.getString("CUST_ASSNNATID"));
        bus.setCustAssnname(rs.getString("CUST_ASSNNAME"));
        return bus;
    }

}
