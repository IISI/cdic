package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.ProvisionMapper;
import tw.com.citi.cdic.batch.dao.ProvisionDao;
import tw.com.citi.cdic.batch.model.Provision;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/26
 */
public class ProvisionDaoImpl extends SimpleJdbcDaoSupport implements
        ProvisionDao {

    @Override
    public Provision findByReferenceNo(String referenceNo) {
        List<Provision> provisions = super.getSimpleJdbcTemplate().query("SELECT * FROM PROVISION WHERE CONTRACT_REFERENCE_NO = ?", new ProvisionMapper(), referenceNo);
        return DataAccessUtils.requiredUniqueResult(provisions);
    }

}
