package tw.com.citi.cdic.batch.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.TableFlowDao;
import tw.com.citi.cdic.batch.model.TableFlow;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/24
 */
public class TableFlowDaoImpl extends SimpleJdbcDaoSupport implements
        TableFlowDao {

    @Override
    public TableFlow getTableFlow() {
        List<TableFlow> results = super.getSimpleJdbcTemplate().query("SELECT * FROM TABLEFLOW", new RowMapper<TableFlow>() {

            @Override
            public TableFlow mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                TableFlow data = new TableFlow();
                data.setCDICFileStatus(rs.getInt("CDICFILESTATUS"));
                data.setCustDate(rs.getDate("CUSTDATE"));
                data.setInitDateTime(rs.getDate("INITDATETIME"));
                data.setInitStatus(rs.getString("INITSTATUS"));
                data.setInitUserId(rs.getString("INITUSERID"));
                data.setStarter(rs.getString("STARTER"));
                return data;
            }});
        return DataAccessUtils.singleResult(results);
    }

}
