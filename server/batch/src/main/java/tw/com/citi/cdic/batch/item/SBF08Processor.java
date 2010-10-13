package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.model.A21;
import tw.com.citi.cdic.batch.model.A22;
import tw.com.citi.cdic.batch.model.A23;
import tw.com.citi.cdic.batch.model.A31;
import tw.com.citi.cdic.batch.model.JointAcclist;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/13
 */
public class SBF08Processor implements ItemProcessor<JointAcclist, A31> {

    private A21Dao a21Dao;

    private A22Dao a22Dao;

    private A23Dao a23Dao;

    private int type;

    @Override
    public A31 process(JointAcclist item) throws Exception {
        A21 a21 = a21Dao.findByCustomerIdAndSrNo(item.getGRB(), item.getAccount(), "A21");
        if (a21 != null) {
            if (type == 1) {
                A31 a31 = new A31();
                a31.setUnit("021");
                a31.setSrNo(item.getAccount());
                a31.setLocateRate(100.00);
                a31.setBranchNo(a21.getBranchNo());
                a31.setCurrencyCode(a21.getCurrencyCode());
                a31.setCustomerId(a21.getCustomerId());
                return a31;
            } else {
                return null;
            }
        }
        
        A21 b21 = a21Dao.findByCustomerIdAndSrNo(item.getGRB(), item.getAccount(), "B21");
        if (b21 != null) {
            if (type == 2) {
                A31 b31 = new A31();
                b31.setUnit("021");
                b31.setSrNo(item.getAccount());
                b31.setLocateRate(100.00);
                b31.setBranchNo(b21.getBranchNo());
                b31.setCurrencyCode(b21.getCurrencyCode());
                b31.setCustomerId(b21.getCustomerId());
                return b31;
            } else {
                return null;
            }
        }
        
        A22 a22 = a22Dao.findByCustomerIdAndSrNo(item.getGRB(), item.getAccount(), "A22");
        if (a22 != null) {
            if (type == 1) {
                A31 a31 = new A31();
                a31.setUnit("021");
                a31.setSrNo(item.getAccount());
                a31.setLocateRate(100.00);
                a31.setBranchNo(a22.getBranchNo());
                a31.setCurrencyCode(a22.getCurrencyCode());
                a31.setCustomerId(a22.getCustomerId());
                return a31;
            } else {
                return null;
            }
        }
        
        A22 b22 = a22Dao.findByCustomerIdAndSrNo(item.getGRB(), item.getAccount(), "B22");
        if (b22 != null) {
            if (type == 2) {
                A31 b31 = new A31();
                b31.setUnit("021");
                b31.setSrNo(item.getAccount());
                b31.setLocateRate(100.00);
                b31.setBranchNo(b22.getBranchNo());
                b31.setCurrencyCode(b22.getCurrencyCode());
                b31.setCustomerId(b22.getCustomerId());
                return b31;
            } else {
                return null;
            }
        }
        
        A23 a23 = a23Dao.findByCustomerIdAndSrNo(item.getGRB(), item.getAccount());
        if (a23 != null) {
            if (type == 1) {
                A31 a31 = new A31();
                a31.setUnit("021");
                a31.setSrNo(item.getAccount());
                a31.setLocateRate(100.00);
                a31.setBranchNo(a23.getBranchNo());
                a31.setCurrencyCode(a23.getCurrencyCode());
                a31.setCustomerId(a23.getCustomerId());
                return a31;
            } else {
                return null;
            }
        }
        
        return null;
    }

    public void setA21Dao(A21Dao a21Dao) {
        this.a21Dao = a21Dao;
    }

    public void setA22Dao(A22Dao a22Dao) {
        this.a22Dao = a22Dao;
    }

    public void setA23Dao(A23Dao a23Dao) {
        this.a23Dao = a23Dao;
    }

    /**
     * type = 1 時，代表要處理 A31 的資料；type = 2 時，代表要處理 B31 的資料。
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

}
