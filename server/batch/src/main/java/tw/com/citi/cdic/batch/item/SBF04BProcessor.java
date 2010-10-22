package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.model.A23;
import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/20
 */
public class SBF04BProcessor implements ItemProcessor<A31, A23> {

    private A23Dao a23Dao;

    @Override
    public A23 process(A31 item) throws Exception {
        A23 a23 = a23Dao.findByCustomerIdAndSrNo(item.getCustomerId(), item.getSrNo());
        if (a23 != null) {
            a23.setJointCode("1");
            return a23;
        }
        return null;
    }

    public void setA23Dao(A23Dao a23Dao) {
        this.a23Dao = a23Dao;
    }

}
