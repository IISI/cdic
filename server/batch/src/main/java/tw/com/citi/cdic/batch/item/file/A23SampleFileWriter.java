package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A23;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/22
 */
public class A23SampleFileWriter extends FlatFileItemWriter<A23> {

    @Override
    public void write(List<? extends A23> items) throws Exception {
        List<A23> samples = new ArrayList<A23>();
        for (A23 item : items) {
            if (item.isSample()) {
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
