package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A74;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class A74SampleFileWriter extends FlatFileItemWriter<A74> {

    @Override
    public void write(List<? extends A74> items) throws Exception {
        List<A74> a74List = new ArrayList<A74>();
        for (A74 item : items) {
            if (item.isWriteSample()) {
                a74List.add(item);
            }
        }
        super.write(a74List);
    }
}
