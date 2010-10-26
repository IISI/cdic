package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.T01;

public class T06Mapper implements RowMapper<T01> {

    @Override
    public T01 mapRow(ResultSet rs, int rowNum) throws SQLException {
        T01 t06 = new T01();
        t06.setCode(rs.getString("CODE"));
        t06.setDescription(rs.getString("DESCRIPTION"));
        return t06;
    }

}
