package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A26;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/31
 */
public class A26SampleCountProcessor implements ItemProcessor<A26, A26> {

    private long processCount;

    private int writeSampleFrequency = 1000;

    @Override
    public A26 process(A26 item) throws Exception {
        processCount++;
        if (processCount % writeSampleFrequency == 1) {
            item.setSample(true);
        }
        return item;
    }

}
