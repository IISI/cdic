package tw.com.citi.cdic.batch.item.file;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import tw.com.citi.cdic.batch.model.SBF21Output;

/**
 * @author Lancelot
 * @since 2010/10/26
 */
public class A73FileWriter extends FlatFileItemWriter<SBF21Output> {

    @Override
    public void write(List<? extends SBF21Output> items) throws Exception {
        List<SBF21Output> a73List = new ArrayList<SBF21Output>();
        for (SBF21Output item : items) {
            if (SBF21Output.TYPE.A73 == item.getType()) {
                a73List.add(item);
            }
        }
        super.write(a73List);
    }

}
