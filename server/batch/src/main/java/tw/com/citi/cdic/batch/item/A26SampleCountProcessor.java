package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A26;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/31
 */
public class A26SampleCountProcessor implements ItemProcessor<A26, A26> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A26 process(A26 item) throws Exception {
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 1) {
            item.setSample(true);
        }
        return item;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
