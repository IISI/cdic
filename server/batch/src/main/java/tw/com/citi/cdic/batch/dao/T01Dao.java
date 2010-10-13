package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.T01;

public interface T01Dao {
    /**
     * @param code
     * @param tableName
     *            用來指定要查哪一個 table。
     * @return 找不到時回傳 null。
     */
    T01 findByCode(String code, String tableName);
}
