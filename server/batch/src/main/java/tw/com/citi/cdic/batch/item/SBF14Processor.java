package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A43;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBF14Processor implements ItemProcessor<A43, A43> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A43 process(A43 item) throws Exception {
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 1) {
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
