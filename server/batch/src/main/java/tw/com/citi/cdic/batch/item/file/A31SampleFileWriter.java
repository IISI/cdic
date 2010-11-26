package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A31;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/26
 */
public class A31SampleFileWriter extends FlatFileItemWriter<A31> {

    @Override
    public void write(List<? extends A31> items) throws Exception {
        List<A31> samples = new ArrayList<A31>();
        for (A31 item : items) {
            if (item.isSample()) {
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
