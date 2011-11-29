package tw.com.citi.cdic.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.ProvisionDao;
import tw.com.citi.cdic.batch.model.A41;
import tw.com.citi.cdic.batch.model.Provision;

/**
 * @author Chih-Liang Chang
 * @since 2011/09/26
 */
public class SBF12AProcessor implements ItemProcessor<A41, A41> {

    protected final Logger logger = LoggerFactory.getLogger(SBF12AProcessor.class);

    private ProvisionDao provisionDao;

    @Override
    public A41 process(A41 item) throws Exception {
        logger.debug("A41 SRNO = {}", item.getSrNo());
        if ("WTDT".equals(item.getCharCode())) {
            item.setEvlRank("5");
        } else if (item.getSrNo().length() < 16) {
            return item;
        } else {
            Provision provision = provisionDao.findByReferenceNo(item.getSrNo().substring(0, 14));
            if (provision != null) {
                if ((provision.getProvision5Secured() != null && provision.getProvision5Secured() > 0)
                        || (provision.getProvision5Clean() != null && provision.getProvision5Clean() > 0)) {
                    item.setEvlRank("5");
                } else if ((provision.getProvision4Secured() != null && provision.getProvision4Secured() > 0)
                        || (provision.getProvision4Clean() != null && provision.getProvision4Clean() > 0)) {
                    item.setEvlRank("4");
                } else if ((provision.getProvision32Secured() != null && provision.getProvision32Secured() > 0)
                        || (provision.getProvision31Secured() != null && provision.getProvision31Secured() > 0)
                        || (provision.getProvision32Clean() != null && provision.getProvision32Clean() > 0)
                        || (provision.getProvision31Clean() != null && provision.getProvision31Clean() > 0)) {
                    item.setEvlRank("3");
                } else if ((provision.getProvision2Secured() != null && provision.getProvision2Secured() > 0)
                        || (provision.getProvision2Clean() != null && provision.getProvision2Clean() > 0)) {
                    item.setEvlRank("2");
                }
            }
        }
        return item;
    }

    public void setProvisionDao(ProvisionDao provisionDao) {
        this.provisionDao = provisionDao;
    }

}
