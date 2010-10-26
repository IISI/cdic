package tw.com.citi.cdic.batch.item.file.transform;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.file.transform.RecursiveCollectionLineAggregator;

import tw.com.citi.cdic.batch.model.SBT06Output;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/18
 */
public class T06RecursiveCollectionLineAggregator implements LineAggregator<SBT06Output> {

    private LineAggregator<T01> delegate = new PassThroughLineAggregator<T01>();

    public void setDelegate(LineAggregator<T01> delegate) {
        this.delegate = delegate;
    }

    @Override
    public String aggregate(SBT06Output item) {
        RecursiveCollectionLineAggregator<T01> aggregator = new RecursiveCollectionLineAggregator<T01>();
        aggregator.setDelegate(delegate);
        return aggregator.aggregate(item.getT06List());
    }

}
