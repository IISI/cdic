package tw.com.citi.cdic.batch.item;

import java.util.List;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

public class T06JdbcBatchItemWriter<T> extends JdbcBatchItemWriter<T> {

    @Override
    public void write(final List<? extends T> items) throws Exception {
        if (items != null && items.size() != 0) {
            for (T item : items) {
                super.write((List<? extends T>) item);
            }
        }
    }
}
