package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.Guarantor;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/29
 */
public interface GuarantorDao {

    List<Guarantor> findByCustomerNo(String customerNo);

}
