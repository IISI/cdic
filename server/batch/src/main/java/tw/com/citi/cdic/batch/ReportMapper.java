package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Report;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/24
 */
public class ReportMapper implements RowMapper<Report> {

    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();
        report.setFile(rs.getString("file"));
        report.setBranch(rs.getString("branch"));
        report.setProductCode(rs.getString("productCode"));
        report.setGl(rs.getString("gl"));
        report.setBalance(rs.getDouble("balance"));
        report.setInterest(rs.getDouble("interest"));
        report.setFee(rs.getDouble("fee"));
        report.setTotalBalance(rs.getDouble("totalBalance"));
        return report;
    }

}
