package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/20
 */
public class SBF18Mapper implements RowMapper<Bus> {

    @Override
    public Bus mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bus bus = new Bus();
        bus.setNatnidRegnnumb(rs.getString("NATNID_REGNNUMB"));
        bus.setCustTitlLine1(rs.getString("CUST_TITL_LINE1"));
        return bus;
    }

}
