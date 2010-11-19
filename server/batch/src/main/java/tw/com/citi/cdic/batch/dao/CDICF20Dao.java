package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.CDICF20;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/19
 */
public interface CDICF20Dao {

    CDICF20 findByCurrencyCode(String currencyCode);

}
