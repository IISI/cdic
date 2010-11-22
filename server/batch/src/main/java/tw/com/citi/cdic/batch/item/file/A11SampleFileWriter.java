package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.utils.MaskUtils;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/22
 */
public class A11SampleFileWriter extends FlatFileItemWriter<A11> {

    @Override
    public void write(List<? extends A11> items) throws Exception {
        List<A11> a11s = new ArrayList<A11>();
        for (A11 a11 : items) {
            if (a11.isSample()) {
                a11.setHeadId(MaskUtils.mask(a11.getHeadId(), 6));
                a11.setCName(MaskUtils.mask(a11.getCName(), 2));
                a11.setCeoCode(MaskUtils.mask(a11.getCeoCode(), 6));
                a11.setCeoName(MaskUtils.mask(a11.getCeoName(), 2));
                a11s.add(a11);
            }
        }
        super.write(a11s);
    }

}
