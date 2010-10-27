package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A24;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/13
 */
public class LusA24Mapper implements RowMapper<A24> {

    @Override
    public A24 mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
