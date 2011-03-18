package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.FMBCDWN4Dao;
import tw.com.citi.cdic.batch.model.FMBCDWN4;
import tw.com.citi.cdic.batch.model.T02;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT02Processor implements ItemProcessor<FMBCDWN4, T02> {

    private StepExecution stepExecution;

    private FMBCDWN4Dao dao;

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
                // table 中無資料，且此次批次處理過程中也無相同的 acct or code 時，新增一筆
                if (exist == null) {
                    ExecutionContext stepContext = stepExecution.getExecutionContext();
                    String temp = stepContext.getString(acct, null);
                    if (temp == null) {
                        t02 = new T02();
                        t02.setAcct(item.getAcct());
                        t02.setIBCode(item.getIBCode());
                        t02.setDescription(item.getDescription());
                        stepContext.putString(acct, acct);
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

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
