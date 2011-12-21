package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
                StringUtils.leftPad(customerNo, 9, "0"));
        Bus bus = null;
        try {
            bus = DataAccessUtils.requiredUniqueResult(buses);
            logger.warn("Find BUS. [CUST_NUMB = {}]", new Object[] { StringUtils.leftPad(customerNo, 9, "0") });
        } catch (Exception e) {
            logger.error("Can't find BUS. [CUST_NUMB = {}]", new Object[] { StringUtils.leftPad(customerNo, 9, "0") });
        }
        return bus;
    }

}
