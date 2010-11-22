package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.CDICT03G;
import tw.com.citi.cdic.batch.model.T01;

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
                byte[] desc = new byte[40];
                for (int i = 0; i < 40; i++) {
                    desc[i] = (byte) 0x20;
                }
                String itemDesc = item.getDescription() == null ? "" : item.getDescription();
                int len = desc.length > itemDesc.getBytes("big5").length ? itemDesc.getBytes("big5").length
                        : desc.length;
                System.arraycopy(itemDesc.getBytes("big5"), 0, desc, 0, len);
                t06.setDescription(new String(desc, "big5"));
                out.add(t06);
            } else if ("MD".equals(item.getSystemId().trim())) {
                for (int i = 0; i < 11; i++) {
                    String index = Integer.toHexString(i + 1).toUpperCase();
                    T01 t06 = new T01();
                    t06.setCode(item.getProductCode() + String.valueOf(index));
                    byte[] desc = new byte[40];
                    for (int j = 0; j < 40; j++) {
                        desc[j] = (byte) 0x20;
                    }
                    String itemDesc = item.getDescription() == null ? "" : item.getDescription();
                    int len = desc.length > itemDesc.getBytes("big5").length ? itemDesc.getBytes("big5").length
                            : desc.length;
                    System.arraycopy(itemDesc.getBytes("big5"), 0, desc, 0, len);
                    t06.setDescription(new String(desc, "big5"));
                    out.add(t06);
                }
            }
        }
        return out;
    }
}
