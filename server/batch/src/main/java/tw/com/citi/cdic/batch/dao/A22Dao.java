package tw.com.citi.cdic.batch.dao;

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

}
