package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.utils.MaskUtils;

public class A24SampleFileWriter extends FlatFileItemWriter<A24> {

    @Override
    public void write(List<? extends A24> items) throws Exception {
        List<A24> samples = new ArrayList<A24>();
        for (A24 item : items) {
            if (item.isSample()) {
                item.setCustomerId(MaskUtils.mask(item.getCustomerId(), 6));
                item.setCustomerName(MaskUtils.mask(item.getCustomerName(), 2));
                samples.add(item);
            }
        }
        super.write(samples);
    }

}
