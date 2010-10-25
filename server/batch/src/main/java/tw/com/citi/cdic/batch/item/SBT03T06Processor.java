package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.SBT06Output;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT03T06Processor implements ItemProcessor<T01, SBT06Output> {

    @Override
    public SBT06Output process(T01 item) throws Exception {
        SBT06Output out = new SBT06Output();
        if (item != null) {
            if ("IM ".equals(item.getCode())) {
                T01 t06 = new T01();
                t06.setCode(item.getCode());
                t06.setDescription(item.getDescription());
                out.getT06List().add(t06);
            } else if ("MD ".equals(item.getCode())) {
                for (int i = 0; i < 11; i++) {
                    String index = Integer.toHexString(i + 1).toUpperCase();
                    T01 t06 = new T01();
                    t06.setCode(String.valueOf(index));
                    t06.setDescription("");
                    out.getT06List().add(t06);
                }
            }
        }
        return out;
    }
}
