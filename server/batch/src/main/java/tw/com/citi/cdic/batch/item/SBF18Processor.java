package tw.com.citi.cdic.batch.item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A24Dao;
import tw.com.citi.cdic.batch.dao.TableFlowDao;
import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.model.A61;
import tw.com.citi.cdic.batch.model.TableFlow;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/14
 */
public class SBF18Processor implements ItemProcessor<A61, A61> {

    protected static final Logger logger = LoggerFactory.getLogger(SBF18Processor.class);

    private A24Dao a24Dao;

    private TableFlowDao tableFlowDao;

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A61 process(A61 a61) throws Exception {
        List<A24> a24s = new ArrayList<A24>();
        
        /*
         * 利用編碼過的 id 去串 A24
         */
        a24s = a24Dao.findByCustomerId(a61.getCustId());
        for (A24 a24 : a24s) {
            // 1. Summary 台幣要保金額
            a61.setAcctBalance(a61.getAcctBalance() + a24.getBalance());
            a61.setAcctInt(a61.getAcctInt() + a24.getIntPayable());
        }
        
        a61.setUnit("021");
        a61.setBranchNo("0000");
        TableFlow tableFlow = tableFlowDao.getTableFlow();
        Date custDate = tableFlow.getCustDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        a61.setDate(sdf.format(custDate));
        
        if (!(a61.getAcctBalance() == 0 && a61.getAcctInt() == 0 && a61.getNoAcctBalance() == 0  && a61.getNoAcctInt() == 0
                && a61.getUnDepBalance() == 0 && a61.getUnDepInt() == 0 && a61.getAcctBalanceEx() == 0 && a61.getAcctIntEx() == 0
                && a61.getNoAcctBalanceEx() == 0 && a61.getNoAcctIntEx() == 0 && a61.getUnDepBalanceEx() == 0 && a61.getUnDepIntEx() == 0
                && a61.getObuDepBalance() == 0 && a61.getObuDepInt() == 0)) {
            ExecutionContext stepContext = stepExecution.getExecutionContext();
            long processCount = stepContext.getLong("PROCESS_COUNT", 0);
            processCount++;
            stepContext.putLong("PROCESS_COUNT", processCount);
            if (processCount % writeSampleFrequency == 1) {
                a61.setSample(true);
            }
        }
        
        return a61;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public void setA24Dao(A24Dao a24Dao) {
        this.a24Dao = a24Dao;
    }

    public void setTableFlowDao(TableFlowDao tableFlowDao) {
        this.tableFlowDao = tableFlowDao;
    }

    public void setWriteSampleFrequency(int writeSampleFrequency) {
        this.writeSampleFrequency = writeSampleFrequency;
    }

}
