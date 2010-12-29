package tw.com.citi.cdic.batch.dao;

import java.util.List;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/29
 */
public interface CifDao {

    List<String> findCustNumbByJointId(String jointId);

}
