package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A77;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBF25Processor implements ItemProcessor<A77, A77> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A77 process(A77 item) throws Exception {
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 0) {
            return item;
        } else {
            return null;
        }
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public void setWriteSampleFrequency(int writeSampleFrequency) {
        this.writeSampleFrequency = writeSampleFrequency;
    }

}
