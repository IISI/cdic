package tw.com.citi.cdic.batch.item;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A24;
import tw.com.citi.cdic.batch.model.Lus;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/19
 */
public class SBF05Processor implements ItemProcessor<Lus, A24> {

    private StepExecution stepExecution;

    private int writeSampleFrequency = 1000;

    @Override
    public A24 process(Lus item) throws Exception {
        A24 a24 = new A24();
        a24.setUnit("021");
        a24.setSrNo(item.getAcctNo());
        a24.setCharCode(item.getProdName());
        a24.setCustomerId(item.getUnino());
        a24.setCustomerName(new String(item.getName().getBytes("ms950"), "ms950"));
        a24.setBalance(item.getBalance());
        a24.setRateType(item.getProdName());
        String address = "";
        if (item.getCommAdr() != null && item.getCommAdr().length() > 6) {
            address = item.getCommAdr().substring(0, 6);
        } else {
            address = item.getCommAdr();
        }
        a24.setAddress(new String(address.getBytes("ms950"), "ms950") + "ＸＸ路ＸＸ巷ＸＸ號ＸＸ樓");
        if (!"LCB".equalsIgnoreCase(item.getClosedBy())) {
            a24.setBranchNo("0018");
            if ("DESA".equals(item.getProdName())) {
                a24.setApNo("2200119000");
            } else if ("NRSA".equals(item.getProdName())) {
                a24.setApNo("2200109000");
            } else if ("PDSA".equals(item.getProdName())) {
                a24.setApNo("2200109000");
            } else if ("SESA".equals(item.getProdName())) {
                a24.setApNo("2200119000");
            } else if ("WESA".equals(item.getProdName())) {
                a24.setApNo("2200119000");
            } else {
                a24.setApNo("");
            }
            a24.setCustomerBusinessCode("000000".equals(item.getNewBCode()) ? "060000" : "014699");
            a24.setCustomerType("000");
            a24.setCurrencyCode("TWD");
            a24.setIntPayable(item.getInterest());
            a24.setType(A24.Type.A);
        } else {
            Configuration config = new HierarchicalINIConfiguration("branch_mappings.ini");
            a24.setBranchNo(config.getString(item.getBranchCode() + ".branchNo"));
            Configuration config2 = new HierarchicalINIConfiguration("account_mappings.ini");
            String currName = item.getCurrName() == null ? "" : item.getCurrName().trim();
            if ("NTD".equals(currName)) {
                currName = "TWD";
            }
            String apNo = "";
            if ("obu".equalsIgnoreCase(item.getObuDbu())) {
                apNo = config2.getString(currName + ".obu");
                a24.setType(A24.Type.C);
            } else {
                apNo = config2.getString(currName + ".dbu");
                a24.setType(A24.Type.B);
            }
            a24.setApNo(apNo);
            a24.setCustomerBusinessCode(item.getbCode());
            a24.setCustomerType(item.getCompany());
            a24.setCurrencyCode(currName);
            if (item.getLegal_adr() != null && item.getLegal_adr().length() > 6) {
                address = item.getLegal_adr().substring(0, 6);
            } else {
                address = item.getLegal_adr();
            }
            a24.setOriginalAddress(new String(address.getBytes("ms950"), "ms950") + "ＸＸ路ＸＸ巷ＸＸ號ＸＸ樓");
        }

        ExecutionContext stepContext = this.stepExecution.getExecutionContext();

        String countKey = "PROCESS_COUNT_" + a24.getType().toString();
        long processCount = stepContext.getLong(countKey, 0);
        processCount++;
        stepContext.putLong(countKey, processCount);
        if (processCount % writeSampleFrequency == 1) {
            a24.setSample(true);
        }

        return a24;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
