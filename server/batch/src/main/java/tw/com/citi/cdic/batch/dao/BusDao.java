package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/29
 */
public interface BusDao {

    Bus findByCustNumb(String customerNo);

}
