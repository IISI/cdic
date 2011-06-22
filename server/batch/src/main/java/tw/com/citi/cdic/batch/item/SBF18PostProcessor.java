package tw.com.citi.cdic.batch.item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.TableFlowDao;
import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.model.A61;
import tw.com.citi.cdic.batch.model.TableFlow;

/**
 * @author Chih-liang Chang
 * @since 2011/6/21
 */
public class SBF18PostProcessor implements ItemProcessor<A24, A61> {

    private TableFlowDao tableFlowDao;

    private Set<String> processedAccount;

    @Override
    public A61 process(A24 item) throws Exception {
        // 如果這筆資料已經被歸戶過了，就 skip 掉
        if (processedAccount.contains(item.getCustomerId())) {
            return null;
        }
        
        A61 a61 = new A61();
        a61.setUnit("021");
        a61.setBranchNo("0000");
        a61.setCustId(item.getCustomerId());
        TableFlow tableFlow = tableFlowDao.getTableFlow();
        Date custDate = tableFlow.getCustDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        a61.setDate(sdf.format(custDate));
        a61.setAcctBalance(item.getBalance());
        a61.setAcctInt(item.getIntPayable());
        return a61;
    }

    public void setTableFlowDao(TableFlowDao tableFlowDao) {
        this.tableFlowDao = tableFlowDao;
    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.processedAccount = (Set<String>) jobContext.get("PROCESSED_ACCOUNT");
    }

}
