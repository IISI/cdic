package tw.com.citi.cdic.batch.item;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.TableFlowDao;
import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.model.A61;
import tw.com.citi.cdic.batch.model.TableFlow;

/**
 * @author Chih-liang Chang
 * @since 2011/6/21
 */
public class SBF18PostProcessor implements ItemProcessor<A24, A61> {

    private TableFlowDao tableFlowDao;

    @Override
    public A61 process(A24 item) throws Exception {
        A61 a61 = new A61();
        a61.setUnit("021");
        a61.setBranchNo("0000");
        a61.setCustId(item.getCustomerId());
        TableFlow tableFlow = tableFlowDao.getTableFlow();
        Date custDate = tableFlow.getCustDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        a61.setDate(sdf.format(custDate));
        a61.setAcctBalance(item.getBalance());
        a61.setAcctInt(item.getIntPayable());
        return a61;
    }

    public void setTableFlowDao(TableFlowDao tableFlowDao) {
        this.tableFlowDao = tableFlowDao;
    }

}
