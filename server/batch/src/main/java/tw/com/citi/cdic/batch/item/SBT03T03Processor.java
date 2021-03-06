package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.CDICT03G;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT03T03Processor implements ItemProcessor<CDICT03G, T01> {

    @Override
    public T01 process(CDICT03G item) throws Exception {
        T01 t03 = null;
        if (item != null && ("IM".equals(item.getSystemId().trim()) || "MD".equals(item.getSystemId().trim()))) {
            t03 = new T01();
            t03.setCode(item.getProductCode());
            t03.setDescription(item.getDescription());
        }
        return t03;
    }
}
