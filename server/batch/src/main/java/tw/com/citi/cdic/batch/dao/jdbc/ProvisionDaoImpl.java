package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.ProvisionMapper;
import tw.com.citi.cdic.batch.dao.ProvisionDao;
import tw.com.citi.cdic.batch.model.Provision;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/26
 */
public class ProvisionDaoImpl extends SimpleJdbcDaoSupport implements ProvisionDao {

    protected final Logger logger = LoggerFactory.getLogger(ProvisionDaoImpl.class);

    @Override
    public Provision findByReferenceNo(String referenceNo) {
        List<Provision> provisions = super.getSimpleJdbcTemplate().query(
                "SELECT * FROM PROVISION WHERE CONTRACT_REFERENCE_NO = ?", new ProvisionMapper(), referenceNo);
        Provision provision = null;
        try {
            provision = DataAccessUtils.requiredUniqueResult(provisions);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Can't find provision. [CONTRACT_REFERENCE_NO = {}]", new Object[] { referenceNo });
        }
        return provision;
    }

}
