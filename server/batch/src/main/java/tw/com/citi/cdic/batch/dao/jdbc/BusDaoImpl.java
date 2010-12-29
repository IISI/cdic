package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.BusMapper;
import tw.com.citi.cdic.batch.dao.BusDao;
import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/2
 */
public class BusDaoImpl extends SimpleJdbcDaoSupport implements BusDao {

    @Override
    public List<Bus> findByNationalId(String nationalId) {
        return super.getSimpleJdbcTemplate().query("SELECT * FROM LOCAL_BUS WHERE NATNID_REGNNUMB = ? ORDER BY CUST_NUMB", new BusMapper(), nationalId);
    }

}
