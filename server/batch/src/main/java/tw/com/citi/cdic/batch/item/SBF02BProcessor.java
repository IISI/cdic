package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.model.A21;
import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/19
 */
public class SBF02BProcessor implements ItemProcessor<A31, A21> {

    private int type;

    private A21Dao a21Dao;

    @Override
    public A21 process(A31 item) throws Exception {
        if (type == 1) {
            A21 a21 = a21Dao.findByCustomerIdAndSrNo(item.getCustomerId(), item.getSrNo(), "A21");
            if (a21 != null) {
                a21.setJointCode("1");
                return a21;
            } else {
                return null;
            }
        } else {
            A21 b21 = a21Dao.findByCustomerIdAndSrNo(item.getCustomerId(), item.getSrNo(), "B21");
            if (b21 != null) {
                b21.setJointCode("1");
                return b21;
            } else {
                return null;
            }
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setA21Dao(A21Dao a21Dao) {
        this.a21Dao = a21Dao;
    }

}
