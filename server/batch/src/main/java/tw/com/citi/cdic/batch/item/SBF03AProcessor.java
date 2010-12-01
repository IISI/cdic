package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A22;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/12
 */
public class SBF03AProcessor implements ItemProcessor<A22, A22> {

    private int type;

    @Override
    public A22 process(A22 item) throws Exception {
        if ("TWD".equals(item.getCurrencyCode())) {
            if (type == 1) {
                return item;
            } else {
                return null;
            }
        } else {
            if (item.getSrNo().startsWith("8")) {
                if (type == 3) {
                    return item;
                } else {
                    return null;
                }
            } else {
                if (type == 2) {
                    return item;
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * 處理 A22 時，type 設成 1；處理 B22 時，type 設成 2；處理 C22 時，type 設成 3。
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

}
