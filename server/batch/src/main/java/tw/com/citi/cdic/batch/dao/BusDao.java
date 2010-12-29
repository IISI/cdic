package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/2
 */
public interface BusDao {

    List<Bus> findByNationalId(String nationalId);

}
