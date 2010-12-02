package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.A22;

/**
 * A22, B22, C22 共用的 DAO 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public interface A22Dao {

    /**
     * @param srNo
     * @param tableName 用來指定要查詢哪一個 table。
     * @return 找不到時回傳 null。
     */
    A22 findBySrNo(String srNo, String tableName);

    /**
     * @param customerId
     * @param srNo
     * @param tableName
     * @return 找不到時回傳 null。
     */
    A22 findByCustomerIdAndSrNo(String customerId, String srNo, String tableName);

    /**
     * 根據 customerId 為條件，找出 amount > 0 的資料。
     * 
     * @param customerId
     * @param tableName
     * @return
     */
    List<A22> findByCustomerId(String customerId, String tableName);

}
