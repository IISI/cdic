package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.model.Lus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/19
 */
public class SBF05Processor implements ItemProcessor<Lus, A24> {

    @Override
    public A24 process(Lus item) throws Exception {
        A24 a24 = new A24();
        a24.setUnit("021");
        a24.setBranchNo("0000");
        a24.setSrNo(item.getAcctNo());
        a24.setCharCode(item.getProdName());
        a24.setRateType(item.getProdName());
        a24.setCustomerId(item.getUnino());
        a24.setCustomerName(new String(item.getName().getBytes("big5"), "big5"));
        a24.setCustomerBusinessCode(item.getNewBCode());
        a24.setBalance(item.getBalance());
        a24.setIntPayable(item.getInterest());
        String address = "";
        if (item.getCommAdr() != null && item.getCommAdr().length() > 6) {
            address = item.getCommAdr().substring(0, 6);
        } else {
            address = item.getCommAdr();
        }
        a24.setAddress(new String(address.getBytes("big5"), "big5") + "ＸＸ路ＸＸ巷ＸＸ號ＸＸ樓");
        return a24;
    }

}
