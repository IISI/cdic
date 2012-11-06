package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A11Dao;
import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.model.CDICF01;

/**
 * @author Yunglin Liu
 * @since 2012/04/11
 */
public class SBF01A2Processor implements ItemProcessor<CDICF01, List<A11>> {

    private A11Dao a11Dao;

    @Override
    public List<A11> process(CDICF01 item) throws Exception {
        List<A11> a11s = new ArrayList<A11>();
        A11 tmp = a11Dao.findById(StringUtils.leftPad(item.getId(), 9, '0'));
        if (tmp == null) {
            for (int i = 0; i < 2; i++) {
                String headId = a11Dao.getSeqHeadId(item.getHeadId());
                A11 a11 = new A11();
                a11.setUnit("021");
                a11.setBranchNo("0000");

                a11.setId(i == 0 ? StringUtils.leftPad(item.getId(), 9, '0') : headId);
                a11.setIdNo("");
                a11.setHeadId(headId);
                a11.setCName(new String(item.getCName().getBytes("ms950"), "ms950"));
                String birthday = item.getBirthday();
                if (birthday == null || birthday.trim().isEmpty()) {
                    birthday = "00000000";
                }
                a11.setBirthDate(birthday);
                a11.setCeoCode(item.getCeoCode());
                a11.setCeoName(item.getCeoName());
                a11.setStatusCode("0004");
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
                a11s.add(a11);
            }
        }
        return a11s;
    }

    public void setA11Dao(A11Dao a11Dao) {
        this.a11Dao = a11Dao;
    }

    public A11Dao getA11Dao() {
        return a11Dao;
    }
}
