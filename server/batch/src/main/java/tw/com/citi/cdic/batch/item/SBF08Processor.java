package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
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
public class SBF08Processor implements ItemProcessor<JointAcclist, List<A31>> {

    private A21Dao a21Dao;

    private A22Dao a22Dao;

    private A23Dao a23Dao;

    private int type;

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public List<A31> process(JointAcclist item) throws Exception {
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        
        Set<String> grbSet;
        Object grbSetObj = stepContext.get("GRB_SET");
        if (grbSetObj != null && grbSetObj instanceof Set) {
            grbSet = (Set<String>) grbSetObj;
        } else {
            grbSet = new HashSet<String>();
        }
        if (grbSet.contains(item.getGRB())) {
            return null;
        } else {
            grbSet.add(item.getGRB());
        }
        stepContext.put("GRB_SET", grbSet);
        
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        
        List<A31> results = new ArrayList<A31>();
        
        if (type == 1) {
            List<A21> a21s = a21Dao.findByCustomerId(item.getGRB(), "A21");
            if (a21s != null && !a21s.isEmpty()) {
                for (A21 a21 : a21s) {
                    A31 a31 = new A31();
                    a31.setUnit("021");
                    a31.setSrNo(a21.getSrNo());
                    a31.setLocateRate(100.00);
                    a31.setBranchNo(a21.getBranchNo());
                    a31.setCurrencyCode(a21.getCurrencyCode());
                    a31.setCustomerId(a21.getCustomerId());
                    
                    processCount++;
                    if (processCount % writeSampleFrequency == 1) {
                        a31.setSample(true);
                    }
                    
                    results.add(a31);
                }
            }
            
            List<A22> a22s = a22Dao.findByCustomerId(item.getGRB(), "A22");
            if (a22s != null && !a22s.isEmpty()) {
                for (A22 a22 : a22s) {
                    A31 a31 = new A31();
                    a31.setUnit("021");
                    a31.setSrNo(a22.getSrNo());
                    a31.setLocateRate(100.00);
                    a31.setBranchNo(a22.getBranchNo());
                    a31.setCurrencyCode(a22.getCurrencyCode());
                    a31.setCustomerId(a22.getCustomerId());
                    
                    processCount++;
                    if (processCount % writeSampleFrequency == 1) {
                        a31.setSample(true);
                    }
                    
                    results.add(a31);
                }
            }
            
            List<A23> a23s = a23Dao.findByCustomerId(item.getGRB());
            if (a23s != null && !a23s.isEmpty()) {
                for (A23 a23 : a23s) {
                    A31 a31 = new A31();
                    a31.setUnit("021");
                    a31.setSrNo(a23.getSrNo());
                    a31.setLocateRate(100.00);
                    a31.setBranchNo(a23.getBranchNo());
                    a31.setCurrencyCode(a23.getCurrencyCode());
                    a31.setCustomerId(a23.getCustomerId());
                    
                    processCount++;
                    if (processCount % writeSampleFrequency == 1) {
                        a31.setSample(true);
                    }
                    
                    results.add(a31);
                }
            }
        }
        
        if (type == 2) {
            List<A21> b21s = a21Dao.findByCustomerId(item.getGRB(), "B21");
            if (b21s != null && !b21s.isEmpty()) {
                for (A21 b21 : b21s) {
                    A31 b31 = new A31();
                    b31.setUnit("021");
                    b31.setSrNo(b21.getSrNo());
                    b31.setLocateRate(100.00);
                    b31.setBranchNo(b21.getBranchNo());
                    b31.setCurrencyCode(b21.getCurrencyCode());
                    b31.setCustomerId(b21.getCustomerId());
                    
                    processCount++;
                    if (processCount % writeSampleFrequency == 1) {
                        b31.setSample(true);
                    }
                    
                    results.add(b31);
                }
            }
            
            List<A22> b22s = a22Dao.findByCustomerId(item.getGRB(), "B22");
            if (b22s != null && !b22s.isEmpty()) {
                for (A22 b22 : b22s) {
                    A31 b31 = new A31();
                    b31.setUnit("021");
                    b31.setSrNo(b22.getSrNo());
                    b31.setLocateRate(100.00);
                    b31.setBranchNo(b22.getBranchNo());
                    b31.setCurrencyCode(b22.getCurrencyCode());
                    b31.setCustomerId(b22.getCustomerId());
                    
                    processCount++;
                    if (processCount % writeSampleFrequency == 1) {
                        b31.setSample(true);
                    }
                    
                    results.add(b31);
                }
            }
        }
        
        stepContext.putLong("PROCESS_COUNT", processCount);
        
        return results.isEmpty() ? null : results;
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

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
