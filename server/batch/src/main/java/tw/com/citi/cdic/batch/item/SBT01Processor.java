package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.SBT01Output;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBT01Processor implements ItemProcessor<T01, SBT01Output> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public SBT01Output process(T01 item) throws Exception {
        SBT01Output out = new SBT01Output();
        out.setT01(item);
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 0) {
            out.setWriteSample(true);
        } else {
            out.setWriteSample(false);
        }
        return out;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    public void setWriteSampleFrequency(int writeSampleFrequency) {
        this.writeSampleFrequency = writeSampleFrequency;
    }

}
