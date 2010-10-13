package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.A21;

/**
 * A21, B21, C21 共用的 DAO 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public interface A21Dao {

    /**
     * @param srNo
     * @param tableName 用來指定要查哪一個 table。
     * @return 找不到時回傳 null。
     */
    A21 findBySrNo(String srNo, String tableName);

    /**
     * @param customerId
     * @param srNo
     * @param tableName
     * @return 找不到時回傳 null。
     */
    A21 findByCustomerIdAndSrNo(String customerId, String srNo, String tableName);

}
