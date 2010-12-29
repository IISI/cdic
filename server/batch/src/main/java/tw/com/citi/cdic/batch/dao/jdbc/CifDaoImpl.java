package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedSingleColumnRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.dao.CifDao;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/29
 */
public class CifDaoImpl extends SimpleJdbcDaoSupport implements CifDao {

    @Override
    public List<String> findCustNumbByJointId(String jointId) {
        return getSimpleJdbcTemplate().query("SELECT CUST_NUMB FROM CIF WHERE JOINT_ID = ?", new ParameterizedSingleColumnRowMapper<String>(), jointId);
    }

}
