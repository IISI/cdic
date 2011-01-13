package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.FileStep;
import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/8
 */
public interface CDICFileStatusDao {

    CDICFileStatus findByFileNo(String fileNo);
    
    CDICFileStatus findByFileNo(FileStep fileStep);
    
    List<CDICFileStatus> findByGroup(String group);

    int update(CDICFileStatus CDICFileStatus);

}
