package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.A11;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/20
 */
public interface A11Dao {

    A11 findByNationalIdAndTitle(String nationalId, String title);

}
