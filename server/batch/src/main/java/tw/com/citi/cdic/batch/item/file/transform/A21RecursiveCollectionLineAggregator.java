package tw.com.citi.cdic.batch.item.file.transform;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.file.transform.RecursiveCollectionLineAggregator;

import tw.com.citi.cdic.batch.model.A21;
import tw.com.citi.cdic.batch.model.SBF18Output;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class A21RecursiveCollectionLineAggregator implements
        LineAggregator<SBF18Output> {

    private LineAggregator<A21> delegate = new PassThroughLineAggregator<A21>();

    private int type;

    public void setDelegate(LineAggregator<A21> delegate) {
        this.delegate = delegate;
    }

    @Override
    public String aggregate(SBF18Output item) {
        RecursiveCollectionLineAggregator<A21> aggregator = new RecursiveCollectionLineAggregator<A21>();
        aggregator.setDelegate(delegate);
        if (type == 1) {
            return aggregator.aggregate(item.getA21List());
        } else if (type == 2) {
            return aggregator.aggregate(item.getB21List());
        } else {
            return aggregator.aggregate(item.getC21List());
        }
    }

    public void setType(int type) {
        this.type = type;
    }

}
