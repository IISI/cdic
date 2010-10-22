package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.model.A22;
import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/19
 */
public class SBF03BProcessor implements ItemProcessor<A31, A22> {

    private A22Dao a22Dao;

    private int type;

    @Override
    public A22 process(A31 item) throws Exception {
        if (type == 1) {
            A22 a22 = a22Dao.findByCustomerIdAndSrNo(item.getCustomerId(), item.getSrNo(), "A22");
            if (a22 != null) {
                a22.setJointCode("1");
                return a22;
            } else {
                return null;
            }
        } else {
            A22 b22 = a22Dao.findByCustomerIdAndSrNo(item.getCustomerId(), item.getSrNo(), "B22");
            if (b22 != null) {
                b22.setJointCode("1");
                return b22;
            } else {
                return null;
            }
        }
    }

    public A22Dao getA22Dao() {
        return a22Dao;
    }

    public void setA22Dao(A22Dao a22Dao) {
        this.a22Dao = a22Dao;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
