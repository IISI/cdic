package tw.com.citi.cdic.batch.item.file.transform;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.file.transform.RecursiveCollectionLineAggregator;

import tw.com.citi.cdic.batch.model.A23;
import tw.com.citi.cdic.batch.model.SBF18Output;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/18
 */
public class A23RecursiveCollectionLineAggregator implements
        LineAggregator<SBF18Output> {

    private LineAggregator<A23> delegate = new PassThroughLineAggregator<A23>();

    public void setDelegate(LineAggregator<A23> delegate) {
        this.delegate = delegate;
    }

    @Override
    public String aggregate(SBF18Output item) {
        RecursiveCollectionLineAggregator<A23> aggregator = new RecursiveCollectionLineAggregator<A23>();
        aggregator.setDelegate(delegate);
        if (item.getA23List() != null && item.getA23List().size() > 0) {
            return aggregator.aggregate(item.getA23List());
        } else {
            return null;
        }
    }

}
