package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT06Processor implements ItemProcessor<T01, List<T01>> {

    @Override
    public List<T01> process(T01 item) throws Exception {
        List<T01> out = new ArrayList<T01>();
        T01 t06 = null;
        if (item != null) {
            t06 = new T01();
            t06.setCode(item.getCode());
            byte[] desc = new byte[40];
            for (int i = 0; i < 40; i++) {
                desc[i] = (byte) 0x20;
            }
            String itemDesc = item.getDescription() == null ? "" : item.getDescription();
            int len = desc.length > itemDesc.getBytes("ms950").length ? itemDesc.getBytes("ms950").length : desc.length;
            System.arraycopy(itemDesc.getBytes("ms950"), 0, desc, 0, len);
            t06.setDescription(new String(desc, "ms950"));
            out.add(t06);
        }
        return out;
    }
}
