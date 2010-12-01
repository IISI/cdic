package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.CDICT03G;
import tw.com.citi.cdic.batch.model.T01;
import tw.com.citi.cdic.batch.utils.Big5StringUtil;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT03T06Processor implements ItemProcessor<CDICT03G, List<T01>> {

    @Override
    public List<T01> process(CDICT03G item) throws Exception {
        List<T01> out = new ArrayList<T01>();
        if (item != null) {
            if ("IM".equals(item.getSystemId().trim())) {
                T01 t06 = new T01();
                t06.setCode(item.getProductCode());
                t06.setDescription(Big5StringUtil.fitStringToLength(item.getDescription(), 40));
                out.add(t06);
            } else if ("MD".equals(item.getSystemId().trim())) {
                for (int i = 0; i < 11; i++) {
                    String index = Integer.toHexString(i + 1).toUpperCase();
                    T01 t06 = new T01();
                    t06.setCode(item.getProductCode() + String.valueOf(index));
                    t06.setDescription(Big5StringUtil.fitStringToLength(item.getDescription(), 40));
                    out.add(t06);
                }
            }
        }
        return out;
    }
}
