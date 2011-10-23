package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A21;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/22
 */
public class A21SampleCountProcessor implements ItemProcessor<A21, A21>, StepExecutionListener {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A21 process(A21 item) throws Exception {
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 1) {
            item.setSample(true);
        }
        return item;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // TODO Auto-generated method stub
        return null;
    }

}
