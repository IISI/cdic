package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A11Mapper;
import tw.com.citi.cdic.batch.dao.A11ADao;
import tw.com.citi.cdic.batch.model.A11;

public class A11ADaoImpl extends SimpleJdbcDaoSupport implements A11ADao {

    @Override
    public List<A11> findByHeadId(String headId) {
        String sql = "SELECT * FROM A11A WHERE CUSTHEADID=?";
        return getSimpleJdbcTemplate().query(sql, new A11Mapper(true), headId);
    }

    @Override
    public A11 findByAccountNo(String srNo) {
        String sql = "SELECT * FROM A11A WHERE CUSTSRNO=?";
        List<A11> a11s = getSimpleJdbcTemplate().query(sql, new A11Mapper(true), srNo);
        if (a11s != null && a11s.size() > 0) {
            return a11s.get(0);
        } else {
            return null;
        }
    }
}
