package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.util.CollectionUtils;

import tw.com.citi.cdic.batch.model.SBF18Output;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class A61SampleFileWriter extends FlatFileItemWriter<SBF18Output> {

    public enum Type {
        A61, A21, B21, C21, A22, B22, C22, A23, A24
    }

    private Type type;

    @Override
    public void write(List<? extends SBF18Output> items) throws Exception {
        List<SBF18Output> a61List = new ArrayList<SBF18Output>();
        for (SBF18Output item : items) {
            if (item.isWriteSample() && hasRecords(item)) {
                a61List.add(item);
            }
        }
        super.write(a61List);
    }

    private boolean hasRecords(SBF18Output item) {
        switch (type) {
        case A61:
            return true;
        case A21:
            return !CollectionUtils.isEmpty(item.getA21List());
        case B21:
            return !CollectionUtils.isEmpty(item.getB21List());
        case C21:
            return !CollectionUtils.isEmpty(item.getC21List());
        case A22:
            return !CollectionUtils.isEmpty(item.getA22List());
        case B22:
            return !CollectionUtils.isEmpty(item.getB22List());
        case C22:
            return !CollectionUtils.isEmpty(item.getC22List());
        case A23:
            return !CollectionUtils.isEmpty(item.getA23List());
        case A24:
            return !CollectionUtils.isEmpty(item.getA24List());
        default:
            return false;
        }
    }

    public void setType(Type type) {
        this.type = type;
    }

}
