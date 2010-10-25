package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.T02;

public class T02Mapper implements RowMapper<T02> {

    @Override
    public T02 mapRow(ResultSet rs, int rowNum) throws SQLException {
        T02 t02 = new T02();
        t02.setAcct(rs.getString("ACCT"));
        t02.setIBCode(rs.getString("IBCODE"));
        t02.setDescription(rs.getString("DESCRIPTION"));
        return t02;
    }

}
