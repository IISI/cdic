package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/17
 */
public class SBF01Processor implements ItemProcessor<Bus, A11> {

    @Override
    public A11 process(Bus item) throws Exception {
        A11 a11 = new A11();
        a11.setUnit("021");
        a11.setBranchNo("0000");
        a11.setId(item.getCustNumb());
        a11.setIdNo("");
        a11.setHeadId(item.getNatnidRegnnumb());
        a11.setCName(new String(item.getCustTitlLine1().getBytes("big5"), "big5"));
        a11.setBirthDate(item.getBirthday());
        a11.setCeoCode(item.getCustAssnnatid());
        a11.setCeoName(item.getCustAssnname());
        a11.setStatusCode(item.getCustStat());
        a11.setBusinessCode(item.getBizType());
        a11.setCreateDate(item.getDateEstb());
        a11.setOriginalAddress("");
        String addr = null;
        if (item.getDsctDescL() != null && item.getDsctDescL().length() > 6) {
            addr = new String(item.getDsctDescL().substring(0, 6).getBytes("big5"), "big5");
        } else {
            addr = new String(item.getDsctDescL().getBytes("big5"), "big5");
        }
        a11.setAddress(addr + "ＸＸ路ＸＸ巷ＸＸ號ＸＸ樓");
        a11.setTel1("");
        a11.setTel2("");
        a11.setEmail("");
        return a11;
    }

}
