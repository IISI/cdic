package tw.com.citi.cdic.batch.item.file;

import java.util.List;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/30
 *
 * @param <T>
 */
public class A44ADbWriter<T> extends JdbcBatchItemWriter<T> {

    @Override
    public void write(List<? extends T> items) throws Exception {
        for (T itemList : items) {
            super.write((List<? extends T>) itemList);
        }
    }

}
