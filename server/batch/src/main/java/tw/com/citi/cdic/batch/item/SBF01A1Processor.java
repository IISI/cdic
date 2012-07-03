package tw.com.citi.cdic.batch.item;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.model.CDICF01;

/**
 * @author Yunglin Liu
 * @since 2012/04/11
 */
public class SBF01A1Processor implements ItemProcessor<CDICF01, A11> {

    @Override
    public A11 process(CDICF01 item) throws Exception {
        A11 a11 = new A11();
        a11.setUnit("021");
        a11.setBranchNo("0000");
        a11.setId(StringUtils.leftPad(item.getId(), 9, '0'));
        a11.setIdNo("");
        a11.setHeadId(item.getHeadId());
        a11.setCName(new String(item.getCName().getBytes("ms950"), "ms950"));
        String birthday = item.getBirthday();
        if (birthday == null || birthday.trim().isEmpty()) {
            birthday = "00000000";
        }
        a11.setBirthDate(birthday);
        a11.setCeoCode(item.getCeoCode());
        a11.setCeoName(item.getCeoName());
        a11.setStatusCode("");
        a11.setBusinessCode(item.getBusCode());
        a11.setCreateDate("00000000");
        a11.setOriginalAddress("");
        String addr = null;
        if (item.getAddress() != null && item.getAddress().length() > 6) {
            addr = new String(item.getAddress().substring(0, 6).getBytes("ms950"), "ms950");
        } else {
            addr = new String(item.getAddress().getBytes("ms950"), "ms950");
        }
        a11.setAddress(addr + "ＸＸ路ＸＸ巷ＸＸ號ＸＸ樓");
        a11.setTel1("");
        a11.setTel2("");
        a11.setEmail("");
        a11.setSample(true);
        return a11;
    }
}
