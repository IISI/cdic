package tw.com.citi.cdic.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.A23;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/20
 */
public class A23Mapper implements RowMapper<A23> {

    @Override
    public A23 mapRow(ResultSet rs, int rowNum) throws SQLException {
        A23 a23 = new A23();
        a23.setUnit(rs.getString("CKUNIT"));
        a23.setBranchNo(rs.getString("CKBRNO"));
        a23.setSrNo(rs.getString("CKSRNO"));
        a23.setApNo(rs.getString("CKAPNO"));
        a23.setCharCode(rs.getString("CKCHARCODE"));
        a23.setStatus(rs.getString("CKSTATUS"));
        a23.setCustomerId(rs.getString("CKCUSTID"));
        a23.setCustomerIdNo(rs.getString("CKCUSTIDNO"));
        a23.setCustomerType(rs.getString("CKCUSTTYPE"));
        a23.setOpenDate(rs.getString("CKOPENDATE"));
        a23.setCurrencyCode(rs.getString("CKCURCODE"));
        a23.setAccountBalance(rs.getDouble("CKACTBAL"));
        a23.setStopPayAmount(rs.getDouble("CKSTOPPAYAMT"));
        a23.setJointCode(rs.getString("CKJOINTCODE"));
        a23.setOverdrawStatus(rs.getString("CKOVRSTATUS"));
        a23.setLastTxDate(rs.getString("CKLASTTXDATE"));
        return a23;
    }

}
