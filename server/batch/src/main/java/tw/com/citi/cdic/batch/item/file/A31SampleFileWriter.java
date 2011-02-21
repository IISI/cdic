package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/26
 */
public class A31SampleFileWriter implements ItemWriter<List<A31>> {

    private FlatFileItemWriter<A31> delegator;

    @Override
    public void write(List<? extends List<A31>> items) throws Exception {
        for (List<A31> itemList : items) {
            List<A31> samples = new ArrayList<A31>();
            for (A31 item : itemList) {
                if (item.isSample()) {
                    samples.add(item);
                }
            }
            delegator.write(samples);
        }
    }

    public void setDelegator(FlatFileItemWriter<A31> delegator) {
        this.delegator = delegator;
    }

}
