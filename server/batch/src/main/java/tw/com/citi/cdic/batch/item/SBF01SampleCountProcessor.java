package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A11;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/20
 */
public class SBF01SampleCountProcessor implements ItemProcessor<A11, A11> {

    private long processCount;

    private int writeSampleFrequency = 1000;

    @Override
    public A11 process(A11 item) throws Exception {
        processCount++;
        if (processCount % writeSampleFrequency == 1) {
            item.setSample(true);
        }
        return item;
    }

}
