package tw.com.citi.cdic.batch.item.file;

import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

/**
 * @author Chih-Liang Chang
 * @since 2011/2/21
 */
public class A31FileWriter<T> extends FlatFileItemWriter<T> {

    @Override
    public void write(List<? extends T> items) throws Exception {
        for (T itemList : items) {
            super.write((List<? extends T>) itemList);
        }
    }

}
