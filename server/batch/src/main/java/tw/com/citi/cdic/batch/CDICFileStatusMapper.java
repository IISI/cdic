package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * @author Chih-Liang Chang
 * @since 2011/11/8
 */
public class CDICFileStatusMapper implements RowMapper<CDICFileStatus> {

    @Override
    public CDICFileStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
        CDICFileStatus fileStatus = new CDICFileStatus();
        fileStatus.setFileNo(rs.getString("FILENO"));
        fileStatus.setFileGroup(rs.getString("FILEGROUP"));
        fileStatus.setSubFile(rs.getString("SUBFILE"));
        fileStatus.setFilename(rs.getString("FILENAME"));
        fileStatus.setStatus(rs.getString("STATUS"));
        fileStatus.setConfirmer(rs.getString("CONFIRMER"));
        fileStatus.setConfirmDateTime(rs.getDate("CONFIRMDATETIME"));
        return fileStatus;
    }

}
