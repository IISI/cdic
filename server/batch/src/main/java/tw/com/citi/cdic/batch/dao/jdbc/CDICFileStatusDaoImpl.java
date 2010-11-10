package tw.com.citi.cdic.batch.dao.jdbc;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import tw.com.citi.cdic.batch.CDICFileStatusMapper;
import tw.com.citi.cdic.batch.FileStep;
import tw.com.citi.cdic.batch.dao.CDICFileStatusDao;
import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/8
 */
public class CDICFileStatusDaoImpl extends SimpleJdbcDaoSupport implements
        CDICFileStatusDao {

    @Override
    public CDICFileStatus findByFileNo(FileStep fileStep) {
        List<CDICFileStatus> results = super.getSimpleJdbcTemplate().query("SELECT * FROM CDICFILESTS WHERE FILENO = ?", new CDICFileStatusMapper(), fileStep.name());
        return DataAccessUtils.singleResult(results);
    }

}
