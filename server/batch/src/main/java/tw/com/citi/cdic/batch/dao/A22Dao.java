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
     * 根據 customerId, jointCode 為條件，找出 amount > 0 的資料。
     * 
     * @param customerId
     * @param jointCode
     * @param tableName
     * @return
     */
    List<A22> findByCustomerIdAndJointCode(String customerId, String jointCode, String tableName);

    /**
     * 根據 customerId 為條件，找出 jointCode = 0，而且 charCode 的前四碼等於 WNCD or WBCD or UBCD or UNCD，而且 amount > 0 的資料。
     * 
     * @param customerId
     * @param tableName
     * @return
     */
    List<A22> findByCustomerIdAndJointCodeAndCharCodeAndAmount(String customerId, String tableName);

    /**
     * 根據 customerId 為條件，找出 jointCode = 0，而且 charCode 的前四碼等於 WNCD or WBCD or UBCD or UNCD 或者 sdCase 前四碼等於 PMPL，而且 amount > 0 的資料。<br />
     * <br />
     * SELECT * FROM tableName<br />
     * WHERE TDCUSTID = :customerId<br />
     * AND TDJOINTCODE = :jointCode<br />
     * AND (TDCHARCODE LIKE 'WNCD%' OR TDCHARCODE LIKE 'WBCD%' OR TDCHARCODE LIKE 'UBCD%' OR TDCHARCODE LIKE 'UNCD%' OR TDSDCASE LIKE 'PMPL%')<br />
     * AND TDAMT > 0
     * 
     * @param customerId
     * @param tableName
     * @return
     */
    List<A22> findByCustomerIdAndJointCodeAndCharCodeOrSdCaseAndAmount(String customerId, String tableName);

    /**
     * 根據 customerId 為條件，找出 amount > 0 的資料。
     * 
     * @param customerId
     * @param tableName
     * @return
     */
    List<A22> findByCustomerId(String customerId, String tableName);

}
