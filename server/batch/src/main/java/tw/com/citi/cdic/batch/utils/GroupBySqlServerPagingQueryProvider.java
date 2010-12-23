package tw.com.citi.cdic.batch.utils;

import org.springframework.batch.item.database.support.SqlPagingQueryUtils;
import org.springframework.batch.item.database.support.SqlServerPagingQueryProvider;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/2
 */
public class GroupBySqlServerPagingQueryProvider extends
        SqlServerPagingQueryProvider {

    private String groupKey;

    @Override
    public String generateFirstPageQuery(int pageSize) {
        StringBuilder sb = new StringBuilder(SqlPagingQueryUtils.generateTopSqlQuery(this, false, buildTopClause(pageSize)));
        sb.insert(sb.indexOf("ORDER BY"), "GROUP BY " + groupKey + " ");
        return sb.toString();
    }

    @Override
    public String generateRemainingPagesQuery(int pageSize) {
        StringBuilder sb = new StringBuilder(SqlPagingQueryUtils.generateTopSqlQuery(this, true, buildTopClause(pageSize)));
        sb.insert(sb.indexOf("ORDER BY"), "GROUP BY " + groupKey + " ");
        return sb.toString();
    }

    private String buildTopClause(int pageSize) {
        return new StringBuilder().append("TOP ").append(pageSize).toString();
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

}
