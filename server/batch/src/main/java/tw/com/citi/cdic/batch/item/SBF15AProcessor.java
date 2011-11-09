package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.BusDao;
import tw.com.citi.cdic.batch.dao.GuarantorDao;
import tw.com.citi.cdic.batch.model.A41;
import tw.com.citi.cdic.batch.model.A44;
import tw.com.citi.cdic.batch.model.Guarantor;

/**
 * @author Chih-Liang Chang
 * @since 2011/9/29
 */
public class SBF15AProcessor implements ItemProcessor<A41, List<A44>> {

    private GuarantorDao guarantorDao;

    private BusDao busDao;

    @Override
    public List<A44> process(A41 item) throws Exception {
        List<A44> a44List = new ArrayList<A44>();
        List<Guarantor> guarantors = guarantorDao.findByCustomerNo(item.getCustId());
        if (guarantors == null || guarantors.isEmpty()) return null;
        for (Guarantor guarantor : guarantors) {
            List<A44> temp = createA44s(item, guarantor);
            a44List.addAll(temp);
        }
        return a44List;
    }

    private List<A44> createA44s(A41 item, Guarantor guarantor) {
        List<A44> a44List = new ArrayList<A44>();
        if (guarantor.getUdf9() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf9());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf9()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf6());
            a44.setRelCode(guarantor.getUdf7());
            a44.setCurCode(guarantor.getUdf10());
            a44.setAmt(guarantor.getUdf11());
            a44.setEffDate(guarantor.getUdf8());
            a44List.add(a44);
        }
        if (guarantor.getUdf15() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf15());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf15()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf13());
            a44.setRelCode(guarantor.getUdf14());
            a44.setCurCode(guarantor.getUdf17());
            a44.setAmt(guarantor.getUdf18());
            a44.setEffDate(guarantor.getUdf16());
            a44List.add(a44);
        }
        if (guarantor.getUdf22() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf22());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf22()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf20());
            a44.setRelCode(guarantor.getUdf21());
            a44.setCurCode(guarantor.getUdf24());
            a44.setAmt(guarantor.getUdf25());
            a44.setEffDate(guarantor.getUdf23());
            a44List.add(a44);
        }
        if (guarantor.getUdf29() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf29());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf29()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf27());
            a44.setRelCode(guarantor.getUdf28());
            a44.setCurCode(guarantor.getUdf31());
            a44.setAmt(guarantor.getUdf32());
            a44.setEffDate(guarantor.getUdf30());
            a44List.add(a44);
        }
        if (guarantor.getUdf36() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf36());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf36()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf34());
            a44.setRelCode(guarantor.getUdf35());
            a44.setCurCode(guarantor.getUdf38());
            a44.setAmt(guarantor.getUdf39());
            a44.setEffDate(guarantor.getUdf37());
            a44List.add(a44);
        }
        if (guarantor.getUdf43() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf43());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf43()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf41());
            a44.setRelCode(guarantor.getUdf42());
            a44.setCurCode(guarantor.getUdf45());
            a44.setAmt(guarantor.getUdf46());
            a44.setEffDate(guarantor.getUdf44());
            a44List.add(a44);
        }
        if (guarantor.getUdf51() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf51());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf51()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf48());
            a44.setRelCode(guarantor.getUdf49());
            a44.setCurCode(guarantor.getUdf53());
            a44.setAmt(guarantor.getUdf54());
            a44.setEffDate(guarantor.getUdf52());
            a44List.add(a44);
        }
        if (guarantor.getUdf58() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf58());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf58()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf56());
            a44.setRelCode(guarantor.getUdf57());
            a44.setCurCode(guarantor.getUdf60());
            a44.setAmt(guarantor.getUdf61());
            a44.setEffDate(guarantor.getUdf59());
            a44List.add(a44);
        }
        if (guarantor.getUdf65() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf65());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf65()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf63());
            a44.setRelCode(guarantor.getUdf64());
            a44.setCurCode(guarantor.getUdf67());
            a44.setAmt(guarantor.getUdf68());
            a44.setEffDate(guarantor.getUdf66());
            a44List.add(a44);
        }
        if (guarantor.getUdf72() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf72());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf72()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf70());
            a44.setRelCode(guarantor.getUdf71());
            a44.setCurCode(guarantor.getUdf74());
            a44.setAmt(guarantor.getUdf75());
            a44.setEffDate(guarantor.getUdf73());
            a44List.add(a44);
        }
        if (guarantor.getUdf79() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf79());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf79()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf77());
            a44.setRelCode(guarantor.getUdf78());
            a44.setCurCode(guarantor.getUdf81());
            a44.setAmt(guarantor.getUdf82());
            a44.setEffDate(guarantor.getUdf80());
            a44List.add(a44);
        }
        if (guarantor.getUdf86() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf86());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf86()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf84());
            a44.setRelCode(guarantor.getUdf85());
            a44.setCurCode(guarantor.getUdf88());
            a44.setAmt(guarantor.getUdf89());
            a44.setEffDate(guarantor.getUdf87());
            a44List.add(a44);
        }
        if (guarantor.getUdf93() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf93());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf93()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf91());
            a44.setRelCode(guarantor.getUdf92());
            a44.setCurCode(guarantor.getUdf95());
            a44.setAmt(guarantor.getUdf96());
            a44.setEffDate(guarantor.getUdf94());
            a44List.add(a44);
        }
        if (guarantor.getUdf100() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf100());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf100()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf98());
            a44.setRelCode(guarantor.getUdf99());
            a44.setCurCode(guarantor.getUdf102());
            a44.setAmt(guarantor.getUdf103());
            a44.setEffDate(guarantor.getUdf101());
            a44List.add(a44);
        }
        if (guarantor.getUdf107() != null) {
            A44 a44 = createA44(item);
            a44.setArantId(guarantor.getUdf107());
            a44.setArantName(busDao.findByCustNumb(guarantor.getUdf107()).getCustTitlLine1());
            a44.setCharCode(guarantor.getUdf105());
            a44.setRelCode(guarantor.getUdf106());
            a44.setCurCode(guarantor.getUdf109());
            a44.setAmt(guarantor.getUdf110());
            a44.setEffDate(guarantor.getUdf108());
            a44List.add(a44);
        }
        return a44List;
    }

    private A44 createA44(A41 item) {
        A44 a44 = new A44();
        a44.setUnit(item.getUnit());
        a44.setBrNo(item.getBranchNo());
        a44.setSrNo(item.getSrNo());
        a44.setSrSubNo(item.getSrSubNo());
        a44.setCustId(item.getCustId());
        return a44;
    }

    public void setGuarantorDao(GuarantorDao guarantorDao) {
        this.guarantorDao = guarantorDao;
    }

    public void setBusDao(BusDao busDao) {
        this.busDao = busDao;
    }

}
