package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.BusMapper;
import tw.com.citi.cdic.batch.dao.BusDao;
import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/30
 */
public class BusDaoImpl extends SimpleJdbcDaoSupport implements BusDao {

    @Override
    public Bus findByCustNumb(String customerNo) {
        List<Bus> buses = getSimpleJdbcTemplate().query("SELECT * FROM BUS WHERE CUST_NUMB = ?", new BusMapper(), customerNo);
        return DataAccessUtils.requiredUniqueResult(buses);
    }

}
