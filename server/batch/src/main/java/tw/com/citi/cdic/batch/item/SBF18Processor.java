package tw.com.citi.cdic.batch.item;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.dao.A24Dao;
import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.model.A21;
import tw.com.citi.cdic.batch.model.A22;
import tw.com.citi.cdic.batch.model.A23;
import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.model.A61;
import tw.com.citi.cdic.batch.model.SBF18Output;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/14
 */
public class SBF18Processor implements ItemProcessor<A11, SBF18Output> {

    private A21Dao a21Dao;

    private A22Dao a22Dao;

    private A23Dao a23Dao;

    private A24Dao a24Dao;

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public SBF18Output process(A11 item) throws Exception {
        double temp7 = 0, temp8 = 0, temp11 = 0, temp12 = 0, temp9 = 0, temp10 = 0, temp13 = 0, temp14 = 0, temp17 = 0, temp18 = 0, temp15 = 0, temp16 = 0, temp19 = 0, temp20 = 0;
        SBF18Output out = new SBF18Output();
        
        // 1. Summary 台幣要保金額
        List<A21> a21s = a21Dao.findByCustomerIdAndJointCode(item.getId(), "0", "A21");
        List<A22> a22s = a22Dao.findByCustomerIdAndJointCode(item.getId(), "0", "A22");
        List<A23> a23s = a23Dao.findByCustomerIdAndJointCode(item.getId(), "0");
        List<A24> a24s = a24Dao.findByCustomerId(item.getId());
        for (A21 a21 : a21s) {
            temp7 += a21.getAccountBalance();
            temp8 += a21.getIntPayable();
        }
        for (A22 a22 : a22s) {
            temp7 += a22.getAmount();
            temp8 += a22.getIntPayable();
        }
        for (A23 a23 : a23s) {
            temp7 += a23.getAccountBalance();
        }
        for (A24 a24 : a24s) {
            temp7 += a24.getBalance();
            temp8 += a24.getIntPayable();
        }
        out.getA21List().addAll(a21s);
        out.getA22List().addAll(a22s);
        out.getA23List().addAll(a23s);
        out.getA24List().addAll(a24s);
        
        // 2. Summary 台幣聯名戶金額
        a21s = a21Dao.findByCustomerIdAndJointCode(item.getId(), "1", "A21");
        a22s = a22Dao.findByCustomerIdAndJointCode(item.getId(), "1", "A22");
        a23s = a23Dao.findByCustomerIdAndJointCode(item.getId(), "1");
        for (A21 a21 : a21s) {
            temp11 += a21.getAccountBalance();
            temp12 += a21.getIntPayable();
        }
        for (A22 a22 : a22s) {
            temp11 += a22.getAmount();
            temp12 += a22.getIntPayable();
        }
        for (A23 a23 : a23s) {
            temp11 += a23.getAccountBalance();
        }
        out.getA21List().addAll(a21s);
        out.getA22List().addAll(a22s);
        out.getA23List().addAll(a23s);
        
        // 3. Summary 台幣非要保金額
        a22s = a22Dao.findByCustomerIdAndJointCodeAndCharCodeAndAmount(item.getId(), "A22");
        for (A22 a22 : a22s) {
            temp9 += a22.getAmount();
            temp10 += a22.getIntPayable();
        }
        out.getA22List().addAll(a22s);
        
        // 4. Summary 外幣要保金額
        a21s = a21Dao.findByCustomerIdAndJointCode(item.getId(), "0", "B21");
        a22s = a22Dao.findByCustomerIdAndJointCode(item.getId(), "0", "B22");
        for (A21 b21 : a21s) {
            temp13 += b21.getAccountBalance();
            temp14 += b21.getIntPayable();
        }
        for (A22 b22 : a22s) {
            temp13 += b22.getAmount();
            temp14 += b22.getIntPayable();
        }
        out.getA21List().addAll(a21s);
        out.getA22List().addAll(a22s);
        
        // 5. Summary 外幣聯名戶金額
        a21s = a21Dao.findByCustomerIdAndJointCode(item.getId(), "1", "B21");
        a22s = a22Dao.findByCustomerIdAndJointCode(item.getId(), "1", "B22");
        for (A21 b21 : a21s) {
            temp17 += b21.getAccountBalance();
            temp18 += b21.getIntPayable();
        }
        for (A22 b22 : a22s) {
            temp17 += b22.getAmount();
            temp18 += b22.getIntPayable();
        }
        out.getA21List().addAll(a21s);
        out.getA22List().addAll(a22s);
        
        // 6. Summary 外幣非要保金額
        a22s = a22Dao.findByCustomerIdAndJointCodeAndCharCodeOrSdCaseAndAmount(item.getId(), "B22");
        for (A22 b22 : a22s) {
            temp15 += b22.getAmount();
            temp16 += b22.getIntPayable();
        }
        out.getA22List().addAll(a22s);
        
        // 7. Summary OBU要保金額
        a21s = a21Dao.findByCustomerId(item.getId(), "C21");
        a22s = a22Dao.findByCustomerId(item.getId(), "C22");
        for (A21 c21 : a21s) {
            temp19 += c21.getAccountBalance();
            temp20 += c21.getIntPayable();
        }
        for (A22 c22 : a22s) {
            temp19 += c22.getAmount();
            temp20 += c22.getIntPayable();
        }
        out.getA21List().addAll(a21s);
        out.getA22List().addAll(a22s);
        
        A61 a61 = new A61();
        a61.setUnit("021");
        a61.setBranchNo("0000");
        a61.setCustId(item.getHeadId());
        a61.setDate(null);
        a61.setAcctBalance(temp7);
        a61.setAcctInt(temp8);
        a61.setNoAcctBalance(temp9);
        a61.setNoAcctInt(temp10);
        a61.setUnDepBalance(temp11);
        a61.setUnDepInt(temp12);
        a61.setAcctBalanceEx(temp13);
        a61.setAcctIntEx(temp14);
        a61.setNoAcctBalanceEx(temp15);
        a61.setNoAcctIntEx(temp16);
        a61.setUnDepBalanceEx(temp17);
        a61.setUnDepIntEx(temp18);
        a61.setObuDepBalance(temp19);
        a61.setObuDepInt(temp20);
        
        out.setA61(a61);
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 0) {
            out.setWriteSample(true);
        }
        
        return out;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
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

    public void setA24Dao(A24Dao a24Dao) {
        this.a24Dao = a24Dao;
    }

    public void setWriteSampleFrequency(int writeSampleFrequency) {
        this.writeSampleFrequency = writeSampleFrequency;
    }

}