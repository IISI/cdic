package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.CDICT27;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT27Processor implements ItemProcessor<CDICT27, T01> {

    @Override
    public T01 process(CDICT27 item) throws Exception {
        T01 t27 = null;
        if (item != null && "IM".equals(item.getSourceIndicator())) {
            t27 = new T01();
            t27.setCode(item.getTransactionCode());
            t27.setDescription(item.getStateMne());
        }
        return t27;
    }
}
