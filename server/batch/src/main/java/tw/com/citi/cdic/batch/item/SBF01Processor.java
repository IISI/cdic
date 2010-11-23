package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.model.Bus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/17
 */
public class SBF01Processor implements ItemProcessor<Bus, A11> {

    private long processCount;

    private int writeSampleFrequency = 1000;

    @Override
    public A11 process(Bus item) throws Exception {
        A11 a11 = new A11();
        a11.setUnit("021");
        a11.setBranchNo("0000");
        a11.setId(item.getCustNumb());
        a11.setIdNo("");
        a11.setHeadId(item.getNatnidRegnnumb());
        a11.setCName(item.getCustTitlLine1());
        a11.setBirthDate(item.getBirthday());
        a11.setCeoCode(item.getCustAssnnatid());
        a11.setCeoName(item.getCustAssnname());
        a11.setStatusCode(item.getCustStat());
        a11.setBusinessCode(item.getBizType());
        a11.setCreateDate(item.getDateEstb());
        a11.setOriginalAddress("");
        a11.setAddress(item.getDsctDescL());
        a11.setTel1("");
        a11.setTel2("");
        a11.setEmail("");
        
        processCount++;
        if (processCount % writeSampleFrequency == 0) {
            a11.setSample(true);
        }
        
        return a11;
    }

}
