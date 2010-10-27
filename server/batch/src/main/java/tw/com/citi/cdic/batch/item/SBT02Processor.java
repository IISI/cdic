package tw.com.citi.cdic.batch.item;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.FMBCDWN4Dao;
import tw.com.citi.cdic.batch.model.FMBCDWN4;
import tw.com.citi.cdic.batch.model.T02;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT02Processor implements ItemProcessor<FMBCDWN4, T02> {

    private FMBCDWN4Dao dao;

    private Set<String> tempKeySet = new HashSet<String>();

    @Override
    public T02 process(FMBCDWN4 item) throws Exception {
        T02 t02 = null;
        if (item != null) {
            if (!"1".equals(item.getRecType())) {
                return null;
            } else {
                String acct = item.getAcct().substring(0, 10);
                String code = item.getIBCode();
                FMBCDWN4 exist = dao.findByAcctAndIBCode(acct, code);
                // table 中無資料，且此次批次處理過程中也無相同的 acct + code 時，新增一筆
                if (exist == null) {
                    if (!tempKeySet.contains(acct + code)) {
                        t02 = new T02();
                        t02.setAcct(item.getAcct());
                        t02.setIBCode(item.getIBCode());
                        t02.setDescription(item.getDescription());
                    }
                }
            }
        }
        return t02;
    }

    public void setDao(FMBCDWN4Dao dao) {
        this.dao = dao;
    }

    public FMBCDWN4Dao getDao() {
        return dao;
    }

    public void setTempKeySet(Set<String> tempKeySet) {
        this.tempKeySet = tempKeySet;
    }

    public Set<String> getTempKeySet() {
        return tempKeySet;
    }
}
