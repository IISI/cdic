package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.A23;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public interface A23Dao {

    /**
     * @param srNo
     * @return 找不到時回傳 null。
     */
    A23 findBySrNo(String srNo);

    /**
     * @param customerId
     * @param srNo
     * @return 找不到時回傳 null。
     */
    A23 findByCustomerIdAndSrNo(String customerId, String srNo);

    /**
     * 根據 customerId 為條件，找出 accountBalance > 0 的資料。
     * 
     * @param customerId
     * @return
     */
    List<A23> findByCustomerId(String customerId);

}
