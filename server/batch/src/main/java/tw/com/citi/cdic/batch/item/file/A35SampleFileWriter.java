package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A35;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class A35SampleFileWriter extends FlatFileItemWriter<A35> {

    @Override
    public void write(List<? extends A35> items) throws Exception {
        List<A35> a35List = new ArrayList<A35>();
        for (A35 item : items) {
            if (item.isWriteSample()) {
                a35List.add(item);
            }
        }
        super.write(a35List);
    }
}
