package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.SBT06Output;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT06Processor implements ItemProcessor<T01, SBT06Output> {

    @Override
    public SBT06Output process(T01 item) throws Exception {
        SBT06Output out = new SBT06Output();
        T01 t06 = null;
        if (item != null) {
            t06 = new T01();
            t06.setCode(item.getCode());
            t06.setDescription(item.getDescription());
            out.getT06List().add(t06);
        }
        return out;
    }
}
