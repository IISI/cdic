package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.GuarantorMapper;
import tw.com.citi.cdic.batch.dao.GuarantorDao;
import tw.com.citi.cdic.batch.model.Guarantor;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/30
 */
public class GuarantorDaoImpl extends SimpleJdbcDaoSupport implements
        GuarantorDao {

    @Override
    public List<Guarantor> findByCustomerNo(String customerNo) {
        return getSimpleJdbcTemplate().query("SELECT * FROM GUARANTOR WHERE V_CUST_NO = ?", new GuarantorMapper(), customerNo);
    }

}
