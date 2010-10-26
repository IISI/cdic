package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.A24;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/14
 */
public interface A24Dao {

    /**
     * @param customerId
     * @return
     */
    List<A24> findByCustomerId(String customerId);

}
