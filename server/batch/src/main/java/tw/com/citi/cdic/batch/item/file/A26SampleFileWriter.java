package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A26;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/26
 */
public class A26SampleFileWriter extends FlatFileItemWriter<A26> {

    @Override
    public void write(List<? extends A26> items) throws Exception {
        List<A26> samples = new ArrayList<A26>();
        for (A26 item : items) {
            if (item.isSample()) {
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
