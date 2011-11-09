package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.Provision;

/**
 * @author Chih-Liang Chang
 * @since 2011/09/23
 */
public interface ProvisionDao {

    Provision findByReferenceNo(String referenceNo);

}
