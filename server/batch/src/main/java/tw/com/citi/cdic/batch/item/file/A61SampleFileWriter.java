package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.SBF18Output;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class A61SampleFileWriter extends FlatFileItemWriter<SBF18Output> {

    @Override
    public void write(List<? extends SBF18Output> items) throws Exception {
        List<SBF18Output> a61List = new ArrayList<SBF18Output>();
        for (SBF18Output item : items) {
            if (item.isWriteSample()) {
                a61List.add(item);
            }
        }
        super.write(a61List);
    }

}
