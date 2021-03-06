package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A61;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class A61SampleFileWriter extends FlatFileItemWriter<A61> {

    @Override
    public void write(List<? extends A61> items) throws Exception {
        List<A61> samples = new ArrayList<A61>();
        for (A61 item : items) {
            if (item.isSample()) {
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
