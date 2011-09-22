package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A21;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/21
 */
public class A21SampleFileWriter extends FlatFileItemWriter<A21> {

    @Override
    public void write(List<? extends A21> items) throws Exception {
        List<A21> samples = new ArrayList<A21>();
        for (A21 item : items) {
            if (item.isSample()) {
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
