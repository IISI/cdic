package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.CDICF02;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/4
 */
public class SBF02Processor implements ItemProcessor<CDICF02, CDICF02> {

    private boolean b21;

    @Override
    public CDICF02 process(CDICF02 item) throws Exception {
        if (b21) {
            if (item.getSrNo().startsWith("9")) {
                return item;
            } else {
                return null;
            }
        } else {
            if (!item.getSrNo().startsWith("9")) {
                return item;
            } else {
                return null;
            }
        }
    }

    public void setB21(boolean b21) {
        this.b21 = b21;
    }

}
