package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.SBF21Output;

/**
 * @author Lancelot
 * @since 2010/10/26
 */
public class B73SampleFileWriter extends FlatFileItemWriter<SBF21Output> {

    @Override
    public void write(List<? extends SBF21Output> items) throws Exception {
        List<SBF21Output> b73List = new ArrayList<SBF21Output>();
        for (SBF21Output item : items) {
            if (item.isWriteSample() && SBF21Output.TYPE.B73 == item.getType()) {
                b73List.add(item);
            }
        }
        super.write(b73List);
    }

}
