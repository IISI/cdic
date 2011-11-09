package tw.com.citi.cdic.batch;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tw.com.citi.cdic.batch.model.Provision;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/26
 */
public class ProvisionMapper implements RowMapper<Provision> {

    @Override
    public Provision mapRow(ResultSet rs, int rowNum) throws SQLException {
        Provision provision = new Provision();
        provision.setContractReferenceNo(rs.getString("CONTRACT_REFERENCE_NO"));
        BigDecimal s2 = rs.getBigDecimal("PROVISION2_SECURED");
        if (s2 == null) {
            provision.setProvision2Secured(null);
        } else {
            provision.setProvision2Secured(s2.doubleValue());
        }
        BigDecimal s31 = rs.getBigDecimal("PROVISION3_1_SECURED");
        if (s31 == null) {
            provision.setProvision31Secured(null);
        } else {
            provision.setProvision31Secured(s31.doubleValue());
        }
        BigDecimal s32 = rs.getBigDecimal("PROVISION3_2_SECURED");
        if (s32 == null) {
            provision.setProvision32Secured(null);
        } else {
            provision.setProvision32Secured(s32.doubleValue());
        }
        BigDecimal s4 = rs.getBigDecimal("PROVISION4_SECURED");
        if (s4 == null) {
            provision.setProvision4Secured(null);
        } else {
            provision.setProvision4Secured(s4.doubleValue());
        }
        BigDecimal s5 = rs.getBigDecimal("PROVISION5_SECURED");
        if (s5 == null) {
            provision.setProvision5Secured(null);
        } else {
            provision.setProvision5Secured(s5.doubleValue());
        }
        BigDecimal c2 = rs.getBigDecimal("PROVISION2_CLEAN");
        if (c2 == null) {
            provision.setProvision2Clean(null);
        } else {
            provision.setProvision2Clean(c2.doubleValue());
        }
        BigDecimal c31 = rs.getBigDecimal("PROVISION3_1_CLEAN");
        if (c31 == null) {
            provision.setProvision31Clean(null);
        } else {
            provision.setProvision31Clean(c31.doubleValue());
        }
        BigDecimal c32 = rs.getBigDecimal("PROVISION3_2_CLEAN");
        if (c32 == null) {
            provision.setProvision32Clean(null);
        } else {
            provision.setProvision32Clean(c32.doubleValue());
        }
        BigDecimal c4 = rs.getBigDecimal("PROVISION4_CLEAN");
        if (c4 == null) {
            provision.setProvision4Clean(null);
        } else {
            provision.setProvision4Clean(c4.doubleValue());
        }
        BigDecimal c5 = rs.getBigDecimal("PROVISION5_CLEAN");
        if (c5 == null) {
            provision.setProvision5Clean(null);
        } else {
            provision.setProvision5Clean(c5.doubleValue());
        }
        return provision;
    }

}
