package tw.com.citi.cdic.batch.item.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import tw.com.citi.cdic.batch.model.A26;

/**
 * @author Chih-Liang Chang
 * @since 2010/12/30
 */
public class A26JdbcWriter implements ItemWriter<A26> {

    private ItemWriter<A26> a26ItemWriter;

    private ItemWriter<A26> b26ItemWriter;

    private ItemWriter<A26> c26ItemWriter;

    @Override
    public void write(List<? extends A26> items) throws Exception {
        List<A26> a26Items = new ArrayList<A26>();
        List<A26> b26Items = new ArrayList<A26>();
        List<A26> c26Items = new ArrayList<A26>();
        
        for (A26 a26 : items) {
            switch (a26.getType()) {
            case A:
                a26Items.add(a26);
                break;
            case B:
                b26Items.add(a26);
                break;
            case C:
                c26Items.add(a26);
                break;
            }
        }
        
        a26ItemWriter.write(a26Items);
        b26ItemWriter.write(b26Items);
        c26ItemWriter.write(c26Items);
    }

    public void setA26ItemWriter(ItemWriter<A26> a26ItemWriter) {
        this.a26ItemWriter = a26ItemWriter;
    }

    public void setB26ItemWriter(ItemWriter<A26> b26ItemWriter) {
        this.b26ItemWriter = b26ItemWriter;
    }

    public void setC26ItemWriter(ItemWriter<A26> c26ItemWriter) {
        this.c26ItemWriter = c26ItemWriter;
    }

}
