package tw.com.citi.cdic.batch.item.file;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2011/2/21
 */
public class A31FileWriter implements ItemWriter<List<A31>> {

    private FlatFileItemWriter<A31> delegator;

    @Override
    public void write(List<? extends List<A31>> items) throws Exception {
        for (List<A31> itemList : items) {
            delegator.write(itemList);
        }
    }

    public void setDelegator(FlatFileItemWriter<A31> delegator) {
        this.delegator = delegator;
    }

}
