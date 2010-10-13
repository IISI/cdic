package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.A23;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public interface A23Dao {

    /**
     * @param srno
     * @return 找不到時回傳 null。
     */
    A23 findBySrNo(String srno);

    /**
     * @param customerId
     * @param srNo
     * @return 找不到時回傳 null。
     */
    A23 findByCustomerIdAndSrNo(String customerId, String srNo);

}
