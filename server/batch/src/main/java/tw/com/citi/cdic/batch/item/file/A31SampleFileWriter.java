package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/26
 */
public class A31SampleFileWriter<T> extends FlatFileItemWriter<T> {

    @Override
    public void write(List<? extends T> items) throws Exception {
        for (T itemList : items) {
            List<A31> samples = new ArrayList<A31>();
            for (A31 item : (List<A31>) itemList) {
                if (item.isSample()) {
                    samples.add(item);
                }
            }
            super.write((List<? extends T>) samples);
        }
    }

}
