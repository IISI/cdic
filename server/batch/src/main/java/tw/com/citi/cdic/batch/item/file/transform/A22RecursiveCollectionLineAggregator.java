package tw.com.citi.cdic.batch.item.file.transform;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.file.transform.RecursiveCollectionLineAggregator;

import tw.com.citi.cdic.batch.model.A22;
import tw.com.citi.cdic.batch.model.SBF18Output;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/18
 */
public class A22RecursiveCollectionLineAggregator implements
        LineAggregator<SBF18Output> {

    private LineAggregator<A22> delegate = new PassThroughLineAggregator<A22>();

    public void setDelegate(LineAggregator<A22> delegate) {
        this.delegate = delegate;
    }

    @Override
    public String aggregate(SBF18Output item) {
        RecursiveCollectionLineAggregator<A22> aggregator = new RecursiveCollectionLineAggregator<A22>();
        aggregator.setDelegate(delegate);
        return aggregator.aggregate(item.getA22List());
    }

}
