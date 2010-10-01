package platform.aquarius.embedserver.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * @author Chih-Liang Chang
 * 
 */
public interface IDao {

    SimpleJdbcTemplate getSimpleJdbcTemplate();

    /**
     * 大量資料的批次更新，更新進行到一半的時候，如果發生錯誤，剩下的資料並不會被更新，但已更新過的資料，則會成功寫入 db。
     * 
     * @param sqlCode
     *            更新語句
     * @param parameterArray
     *            更新語句的參數
     * @return 回傳成功的筆數，以及每一筆分別更新了幾筆資料
     */
    int[] batchUpdate(String sqlCode, Map<String, ?>[] parameterArray);

    /**
     * 大量資料的批次更新，更新進行到一半的時候，如果發生錯誤，剩下的資料並不會被更新，但已更新過的資料，則會成功寫入 db。
     * 
     * @param sqlCode
     * @param pojoArray
     * @return
     */
    int[] batchUpdate(String sqlCode, Object[] pojoArray);

    /**
     * @param <T>
     *            查詢結果的回傳類別
     * @param sqlCode
     *            查詢語句
     * @param rowMapper
     *            將查詢結果轉換成所需類別的程式
     * @param parameters
     *            查詢語句的參數
     * @return
     */
    <T> List<T> query(String sqlCode, RowMapper<T> rowMapper,
            Map<String, ?> parameters);

    /**
     * @param <T>
     *            查詢結果的回傳類別
     * @param sqlCode
     *            查詢語句
     * @param requiredType
     *            回傳類別
     * @param parameters
     *            查詢語句的參數
     * @return
     */
    <T> List<T> query(String sqlCode, Class<T> requiredType,
            Map<String, ?> parameters);

    /**
     * @param <T>
     *            查詢結果的回傳類別
     * @param sqlCode
     *            查詢語句
     * @param requiredType
     *            回傳類別
     * @param pojo
     *            查詢參數
     * @return
     */
    <T> List<T> query(String sqlCode, Class<T> requiredType, Object pojo);

    /**
     * @param sqlCode
     *            查詢語句
     * @param parameters
     *            查詢語句的參數
     * @return
     */
    List<Map<String, Object>> queryForList(String sqlCode,
            Map<String, ?> parameters);

    /**
     * @param sqlCode
     * @param parameters
     * @return
     */
    long queryForLong(String sqlCode, Map<String, ?> parameters);

    /**
     * @param sqlCode
     *            更新語句，例如 insert, update, delete
     * @param parameters
     *            更新語句的參數
     * @return
     */
    int update(String sqlCode, Map<String, ?> parameters);

    /**
     * 直接使用 pojo 物件當做更新的參數。
     * 
     * @param sqlCode
     *            更新語句，例如 insert, update, delete
     * @param pojo
     * @return
     */
    int update(String sqlCode, Object pojo);

    /**
     * 多筆 sql 更新需要在同一個 db transaction 內被執行的時候，可以使用這支程式來確保所有的指令都一起成功或一起失敗。
     * 
     * @param sqlCodeArray
     *            多筆更新語句，例如 insert, update, delete
     * @param parametersArray
     *            多筆更新語句的參數
     * @return
     */
    int[] update(String[] sqlCodeArray, Map<String, ?>[] parametersArray);

    /**
     * 多筆 sql 更新需要在同一個 db transaction 內被執行的時候，可以使用這支程式來確保所有的指令都一起成功或一起失敗。
     * 
     * @param sqlCode
     * @param parametersArray
     * @return
     */
    int[] update(final String sqlCode, final Map<String, ?>[] parametersArray);

    /**
     * @param sqlCodeArray
     *            多筆更新語句，例如 insert, update, delete
     * @param pojoArray
     * @return
     */
    int[] update(String[] sqlCodeArray, Object[] pojoArray);

    /**
     * @param sqlCodeArray
     *            多筆更新語句，例如 insert, update, delete
     * @param pojo
     * @param parameters
     * @return PK value
     */
    public long update(final String[] sqlCodeArray, final Object pojo,
            final Map<String, ?> parameters);

}
