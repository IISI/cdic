package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A73;
import tw.com.citi.cdic.batch.model.SBF21Output;

/**
 * @author Lancelot
 * @since 2010/10/26
 */
public class SBF21A73Processor implements ItemProcessor<A73, SBF21Output> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public SBF21Output process(A73 item) throws Exception {
        SBF21Output out = new SBF21Output();
        out.setRecord(item);
        out.setType(SBF21Output.TYPE.A73);
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        if (processCount % writeSampleFrequency == 1) {
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
