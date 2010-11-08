package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.FileStep;
import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/8
 */
public interface CDICFileStatusDao {

    CDICFileStatus findByFileNo(FileStep fileStep);

}
