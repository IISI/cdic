package tw.com.citi.cdic.batch.item.file.transform;

import java.util.List;

import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.item.file.transform.RecursiveCollectionLineAggregator;

import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/18
 */
public class T06RecursiveCollectionLineAggregator implements LineAggregator<List<T01>> {

    private LineAggregator<T01> delegate = new PassThroughLineAggregator<T01>();

    public void setDelegate(LineAggregator<T01> delegate) {
        this.delegate = delegate;
    }

    @Override
    public String aggregate(List<T01> items) {
        RecursiveCollectionLineAggregator<T01> aggregator = new RecursiveCollectionLineAggregator<T01>();
        aggregator.setDelegate(delegate);
        return aggregator.aggregate(items);
    }

}
