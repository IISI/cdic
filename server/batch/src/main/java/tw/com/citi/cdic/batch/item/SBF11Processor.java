package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A35;
import tw.com.citi.cdic.batch.model.SBF11Output;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBF11Processor implements ItemProcessor<A35, SBF11Output> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public SBF11Output process(A35 item) throws Exception {
        SBF11Output out = new SBF11Output();
        out.setA35(item);
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
