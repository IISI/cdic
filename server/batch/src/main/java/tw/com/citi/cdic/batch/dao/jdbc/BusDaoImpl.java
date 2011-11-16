package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected final Logger logger = LoggerFactory.getLogger(BusDaoImpl.class);

    @Override
    public Bus findByCustNumb(String customerNo) {
        List<Bus> buses = getSimpleJdbcTemplate().query("SELECT * FROM BUS WHERE CUST_NUMB = ?", new BusMapper(),
                customerNo);
        Bus bus = null;
        try {
            bus = DataAccessUtils.requiredUniqueResult(buses);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Can't find BUS. [CUST_NUMB = {}]", new Object[] { customerNo });
        }
        return bus;
    }

}
