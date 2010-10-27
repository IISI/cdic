package platform.aquarius.embedserver.jdbc;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import platform.aquarius.def.PropertiesManager;

/**
 * @author Chih-Liang Chang
 * 
 */
public class Dao implements IDao {

    public static final String SORT_INDEX = "sidx";

    public static final String SORT_DIR = "sord";

    public static final int SORT_INDEX_LEN = SORT_INDEX.length();

    public static final int SORT_DIR_LEN = SORT_DIR.length();

    private SimpleJdbcTemplate jdbcTemplate;

    private TransactionTemplate txTemplate;

    private Properties sqlProps;

    /**
     * Get data source from JNDI.
     * 
     * @param jndiName
     *            ex. java:comp/env/jdbc/DataSource
     */
    public Dao(String jndiName) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(jndiName);
            jdbcTemplate = new SimpleJdbcTemplate(ds);
            PlatformTransactionManager txManager = new DataSourceTransactionManager(
                    ds);
            txTemplate = new TransactionTemplate(txManager);
            init();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao(DataSource ds) {
        jdbcTemplate = new SimpleJdbcTemplate(ds);
        PlatformTransactionManager txManager = new DataSourceTransactionManager(
                ds);
        txTemplate = new TransactionTemplate(txManager);
        init();
    }

    private void init() {
        try {
            this.sqlProps = PropertiesManager.getSqlProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public int[] batchUpdate(String sqlCode, Map<String, ?>[] parameterArray) {
        return jdbcTemplate.batchUpdate(sqlProps.getProperty(sqlCode),
                parameterArray);
    }

    @Override
    public int[] batchUpdate(String sqlCode, Object[] pojoArray) {
        SqlParameterSource[] batchArgs = SqlParameterSourceUtils
                .createBatch(pojoArray);
        return jdbcTemplate.batchUpdate(sqlProps.getProperty(sqlCode),
                batchArgs);
    }

    @Override
    public <T> List<T> query(String sqlCode, RowMapper<T> rowMapper,
            Map<String, ?> parameters) {
        return jdbcTemplate.query(sqlProps.getProperty(sqlCode), rowMapper,
                parameters);
    }

    private String makeSql(String sqlCode, Object sortIndex, Object sortDir) {
        StringBuilder sql = new StringBuilder(sqlProps.getProperty(sqlCode));

        int sortIndexPos = sql.indexOf(":" + SORT_INDEX);
        if (sortIndexPos != -1 && sortIndex != null) {
            sql.replace(sortIndexPos, sortIndexPos + SORT_INDEX_LEN + 1,
                    (String) sortIndex);
        }

        int sortDirPos = sql.indexOf(":" + SORT_DIR);
        if (sortDirPos != -1 && sortDir != null) {
            sql.replace(sortDirPos, sortDirPos + SORT_DIR_LEN + 1,
                    (String) sortDir);
        }

        return sql.toString();
    }

    @Override
    public <T> List<T> query(String sqlCode, Class<T> requiredType,
            Map<String, ?> parameters) {
        return jdbcTemplate.query(
                makeSql(sqlCode, parameters.get(SORT_INDEX),
                        parameters.get(SORT_DIR)),
                new BeanPropertyRowMapper<T>(requiredType), parameters);
    }

    @Override
    public <T> List<T> query(String sqlCode, Class<T> requiredType, Object pojo) {
        return jdbcTemplate.query(sqlProps.getProperty(sqlCode),
                new BeanPropertyRowMapper<T>(requiredType),
                new BeanPropertySqlParameterSource(pojo));
    }

    @Override
    public List<Map<String, Object>> queryForList(String sqlCode,
            Map<String, ?> parameters) {
        return jdbcTemplate.queryForList(sqlProps.getProperty(sqlCode),
                parameters);
    }

    @Override
    public long queryForLong(String sqlCode, Map<String, ?> parameters) {
        return jdbcTemplate.queryForLong(sqlProps.getProperty(sqlCode),
                parameters);
    }

    @Override
    public int update(final String sqlCode, final Map<String, ?> parameters) {
        return txTemplate.execute(new TransactionCallback<Integer>() {

            @Override
            public Integer doInTransaction(TransactionStatus status) {
                return jdbcTemplate.update(sqlProps.getProperty(sqlCode),
                        parameters);
            }
        });
    }

    @Override
    public int update(final String sqlCode, final Object pojo) {
        return txTemplate.execute(new TransactionCallback<Integer>() {

            @Override
            public Integer doInTransaction(TransactionStatus status) {
                return jdbcTemplate.update(sqlProps.getProperty(sqlCode),
                        new BeanPropertySqlParameterSource(pojo));
            }
        });
    }

    @Override
    public int[] update(final String[] sqlCodeArray,
            final Map<String, ?>[] parametersArray) {
        Assert.notNull(sqlCodeArray);
        Assert.notNull(parametersArray);

        if (sqlCodeArray.length != parametersArray.length) {
            throw new RuntimeException(
                    "The length of sql array and parameters array do not match.");
        }

        return txTemplate.execute(new TransactionCallback<int[]>() {

            @Override
            public int[] doInTransaction(TransactionStatus status) {
                int[] count = new int[sqlCodeArray.length];

                for (int i = 0; i < sqlCodeArray.length; i++) {
                    count[i] = jdbcTemplate.update(
                            sqlProps.getProperty(sqlCodeArray[i]),
                            parametersArray[i]);
                }

                return count;
            }
        });
    }

    @Override
    public int[] update(final String sqlCode,
            final Map<String, ?>[] parametersArray) {
        Assert.notNull(sqlCode);
        Assert.notNull(parametersArray);

        return txTemplate.execute(new TransactionCallback<int[]>() {

            @Override
            public int[] doInTransaction(TransactionStatus status) {
                int[] count = new int[parametersArray.length];

                for (int i = 0; i < parametersArray.length; i++) {
                    count[i] = jdbcTemplate.update(
                            sqlProps.getProperty(sqlCode), parametersArray[i]);
                }

                return count;
            }
        });
    }

    @Override
    public int[] update(final String[] sqlCodeArray, final Object[] pojoArray) {
        if (sqlCodeArray == null) {
            return new int[0];
        }
        Assert.notNull(pojoArray);

        if (sqlCodeArray.length != pojoArray.length) {
            throw new RuntimeException(
                    "The length of sql array and parameters array do not match.");
        }

        return txTemplate.execute(new TransactionCallback<int[]>() {

            @Override
            public int[] doInTransaction(TransactionStatus status) {
                int[] count = new int[sqlCodeArray.length];

                for (int i = 0; i < sqlCodeArray.length; i++) {
                    count[i] = jdbcTemplate.update(
                            sqlProps.getProperty(sqlCodeArray[i]),
                            new BeanPropertySqlParameterSource(pojoArray[i]));
                }

                return count;
            }
        });
    }

    @Override
    public long update(final String[] sqlCodeArray, final Object pojo,
            final Map<String, ?> parameters) {

        return txTemplate.execute(new TransactionCallback<Long>() {

            @Override
            public Long doInTransaction(TransactionStatus status) {
                Long lastValue = -1L;
                int count = jdbcTemplate.update(
                        sqlProps.getProperty(sqlCodeArray[0]),
                        new BeanPropertySqlParameterSource(pojo));
                if (count == 1) {
                    lastValue = jdbcTemplate.queryForLong(
                            sqlProps.getProperty(sqlCodeArray[1]), parameters);
                } else {
                    lastValue = -1L;
                }
                return lastValue;
            }
        });
    }
}
