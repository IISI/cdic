package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A21;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/4
 */
public class SBF02AProcessor implements ItemProcessor<A21, A21> {

    private boolean b21;

    @Override
    public A21 process(A21 item) throws Exception {
        if (b21) {
            if (!item.getSrNo().startsWith("8")) {
                return item;
            } else {
                return null;
            }
        } else {
            if (item.getSrNo().startsWith("8")) {
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
