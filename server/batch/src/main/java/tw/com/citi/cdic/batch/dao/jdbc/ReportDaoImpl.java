package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.ReportMapper;
import tw.com.citi.cdic.batch.dao.ReportDao;
import tw.com.citi.cdic.batch.model.Report;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/24
 */
public class ReportDaoImpl extends SimpleJdbcDaoSupport implements ReportDao {

    @Override
    public List<Report> findAll() {
        List<Report> results = new ArrayList<Report>();
        ReportMapper mapper = new ReportMapper();
        
        List<Report> a21s = super.getSimpleJdbcTemplate().query("SELECT 'FILE2' 'file', PBBRNO branch, PBCHARCODE productCode, PBAPNO gl, SUM(PBACTBAL) balance, SUM(PBINTPAYABLE) interest, SUM(PBACTBAL) + SUM(PBINTPAYABLE) totalBalance FROM A21 GROUP BY PBBRNO, PBCHARCODE, PBAPNO ORDER BY PBBRNO, PBCHARCODE, PBAPNO", mapper);
        List<Report> b21s = super.getSimpleJdbcTemplate().query("SELECT 'FILE2' 'file', PBBRNO branch, PBCHARCODE productCode, PBAPNO gl, SUM(PBACTBAL) balance, SUM(PBINTPAYABLE) interest, SUM(PBACTBAL) + SUM(PBINTPAYABLE) totalBalance FROM B21 GROUP BY PBBRNO, PBCHARCODE, PBAPNO ORDER BY PBBRNO, PBCHARCODE, PBAPNO", mapper);
        List<Report> c21s = super.getSimpleJdbcTemplate().query("SELECT 'FILE2' 'file', PBBRNO branch, PBCHARCODE productCode, PBAPNO gl, SUM(PBACTBAL) balance, SUM(PBINTPAYABLE) interest, SUM(PBACTBAL) + SUM(PBINTPAYABLE) totalBalance FROM C21 GROUP BY PBBRNO, PBCHARCODE, PBAPNO ORDER BY PBBRNO, PBCHARCODE, PBAPNO", mapper);
        List<Report> a22s = super.getSimpleJdbcTemplate().query("SELECT 'FILE3' 'file', TDBRNO branch, TDCHARCODE productCode, TDAPNO gl, SUM(TDAMT) balance, SUM(TDINTPAYABLE) interest, SUM(TDAMT) + SUM(TDINTPAYABLE) totalBalance FROM A22 GROUP BY TDBRNO, TDCHARCODE, TDAPNO ORDER BY TDBRNO, TDCHARCODE, TDAPNO", mapper);
        List<Report> b22s = super.getSimpleJdbcTemplate().query("SELECT 'FILE3' 'file', TDBRNO branch, TDCHARCODE productCode, TDAPNO gl, SUM(TDAMT) balance, SUM(TDINTPAYABLE) interest, SUM(TDAMT) + SUM(TDINTPAYABLE) totalBalance FROM B22 GROUP BY TDBRNO, TDCHARCODE, TDAPNO ORDER BY TDBRNO, TDCHARCODE, TDAPNO", mapper);
        List<Report> c22s = super.getSimpleJdbcTemplate().query("SELECT 'FILE3' 'file', TDBRNO branch, TDCHARCODE productCode, TDAPNO gl, SUM(TDAMT) balance, SUM(TDINTPAYABLE) interest, SUM(TDAMT) + SUM(TDINTPAYABLE) totalBalance FROM C22 GROUP BY TDBRNO, TDCHARCODE, TDAPNO ORDER BY TDBRNO, TDCHARCODE, TDAPNO", mapper);
        List<Report> a23s = super.getSimpleJdbcTemplate().query("SELECT 'FILE4' 'file', CKBRNO branch, CKCHARCODE productCode, CKAPNO gl, SUM(CKACTBAL) balance, 0 interest, SUM(CKACTBAL) totalBalance FROM A23 GROUP BY CKBRNO, CKCHARCODE, CKAPNO ORDER BY CKBRNO, CKCHARCODE, CKAPNO", mapper);
        List<Report> a24s = super.getSimpleJdbcTemplate().query("SELECT 'FILE5' 'file', STBRNO branch, STCHARCODE productCode, STAPNO gl, SUM(STBAL) balance, SUM(STINTPAYABLE) interest, SUM(STBAL) + SUM(STINTPAYABLE) totalBalance FROM A24 GROUP BY STBRNO, STCHARCODE, STAPNO ORDER BY STBRNO, STCHARCODE, STAPNO", mapper);
        List<Report> a26s = super.getSimpleJdbcTemplate().query("SELECT 'FILE7' 'file', OTBRNO branch, '' productCode, OTAPNO gl, SUM(OTPAYSAV) balance, 0 interest, SUM(OTPAYSAV) totalBalance FROM A26 GROUP BY OTBRNO, OTAPNO ORDER BY OTBRNO, OTAPNO", mapper);
        List<Report> b26s = super.getSimpleJdbcTemplate().query("SELECT 'FILE7' 'file', OTBRNO branch, '' productCode, OTAPNO gl, SUM(OTPAYSAV) balance, 0 interest, SUM(OTPAYSAV) totalBalance FROM B26 GROUP BY OTBRNO, OTAPNO ORDER BY OTBRNO, OTAPNO", mapper);
        List<Report> c26s = super.getSimpleJdbcTemplate().query("SELECT 'FILE7' 'file', OTBRNO branch, '' productCode, OTAPNO gl, SUM(OTPAYSAV) balance, 0 interest, SUM(OTPAYSAV) totalBalance FROM C26 GROUP BY OTBRNO, OTAPNO ORDER BY OTBRNO, OTAPNO", mapper);
        List<Report> a41s = super.getSimpleJdbcTemplate().query("SELECT 'FILE12' 'file', BRANCH_NO branch, CHAR_CODE productCode, AP_NO gl, SUM(CURRENT_BALANCE) balance, SUM(INT_RECEIVABLE) interest, SUM(CURRENT_BALANCE) + SUM(INT_RECEIVABLE) totalBalance FROM A41 GROUP BY BRANCH_NO, CHAR_CODE, AP_NO ORDER BY BRANCH_NO, CHAR_CODE, AP_NO", mapper);
        
        results.addAll(a21s);
        results.addAll(b21s);
        results.addAll(c21s);
        results.addAll(a22s);
        results.addAll(b22s);
        results.addAll(c22s);
        results.addAll(a23s);
        results.addAll(a24s);
        results.addAll(a26s);
        results.addAll(b26s);
        results.addAll(c26s);
        results.addAll(a41s);
        return results;
    }

}
