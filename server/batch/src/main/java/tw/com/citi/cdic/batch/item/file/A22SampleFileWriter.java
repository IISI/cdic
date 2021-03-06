package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A22;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/21
 */
public class A22SampleFileWriter extends FlatFileItemWriter<A22> {

    @Override
    public void write(List<? extends A22> items) throws Exception {
        List<A22> samples = new ArrayList<A22>();
        for (A22 item : items) {
            if (item.isSample()) {
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
