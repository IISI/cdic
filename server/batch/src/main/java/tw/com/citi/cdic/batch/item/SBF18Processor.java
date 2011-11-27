package tw.com.citi.cdic.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A61;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/14
 */
public class SBF18Processor implements ItemProcessor<A61, A61> {

    protected static final Logger logger = LoggerFactory.getLogger(SBF18Processor.class);

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A61 process(A61 a61) throws Exception {
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

    public void setWriteSampleFrequency(int writeSampleFrequency) {
        this.writeSampleFrequency = writeSampleFrequency;
    }

}
