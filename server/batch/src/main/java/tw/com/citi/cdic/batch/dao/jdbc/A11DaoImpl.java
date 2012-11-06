package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.A11Mapper;
import tw.com.citi.cdic.batch.dao.A11Dao;
import tw.com.citi.cdic.batch.model.A11;

public class A11DaoImpl extends SimpleJdbcDaoSupport implements A11Dao {

    @Override
    public A11 findById(String id) {
        String sql = "SELECT * FROM A11 WHERE CUSTID=?";
        List<A11> a11s = getSimpleJdbcTemplate().query(sql, new A11Mapper(), id);
        if (a11s != null && a11s.size() > 0) {
            return a11s.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String getSeqHeadId(String headId) {
        String sql = "SELECT CUSTID FROM A11 WHERE CUSTID LIKE ? ORDER BY CUSTID DESC";
        List<String> headIds = getSimpleJdbcTemplate().query(sql, new SingleColumnRowMapper<String>(), headId + "%");
        if (headIds != null && headIds.size() > 0) {
            String lastId = headIds.get(0);
            if (lastId.trim().length() > 10) {
                char idx = lastId.charAt(10);
                return headId + String.valueOf(new char[] { (char) (idx + 1) });
            } else {
                return headId + "1";
            }
        } else {
            return headId;
        }
    }

}
