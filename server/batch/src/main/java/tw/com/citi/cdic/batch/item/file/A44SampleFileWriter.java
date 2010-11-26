package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A44;
import tw.com.citi.cdic.batch.utils.MaskUtils;

/**
 * @author Lancelot
 * @since 2010/11/26
 */
public class A44SampleFileWriter extends FlatFileItemWriter<A44> {

    @Override
    public void write(List<? extends A44> items) throws Exception {
        List<A44> a44s = new ArrayList<A44>();
        for (A44 a44 : items) {
            a44.setCustId(MaskUtils.mask(a44.getCustId(), 6));
            a44.setArantId(MaskUtils.mask(a44.getArantId(), 6));
            a44.setArantName(MaskUtils.mask(a44.getArantName(), 2));
            a44s.add(a44);
        }
        super.write(a44s);
    }

}
