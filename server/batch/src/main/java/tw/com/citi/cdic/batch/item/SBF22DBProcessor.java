package tw.com.citi.cdic.batch.item;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.model.A74;
import tw.com.citi.cdic.batch.model.CDICF22R;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBF22DBProcessor implements ItemProcessor<CDICF22R, A74> {

    private StepExecution stepExecution;

    @Override
    public A74 process(CDICF22R item) throws Exception {
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        long processCount = stepContext.getLong("PROCESS_COUNT", 0);
        processCount++;
        stepContext.putLong("PROCESS_COUNT", processCount);
        A74 a74 = null;
        if (processCount > 1) {
            if (item != null) {
                a74 = new A74();
                if ("D".equals(item.getRecType())) {
                    a74.setUnit("021");
                    a74.setBranchNo("0000");
                    a74.setRateType(item.getProdCode());
                    a74.setType("N".equals(item.getRateType()) ? "1" : "2");
                    String period = "";
                    switch (item.getTenorUnit()) {
                    case 1:
                        period = "D";
                        break;
                    case 2:
                        period = "W";
                        break;
                    case 3:
                        period = "M";
                        break;
                    case 4:
                        period = "Y";
                        break;
                    }
                    a74.setPeriod(period);
                    a74.setEffectiveDate(item.getTimestamp() == null ? "00000000" : item.getTimestamp().substring(0, 8));
                    if ("CHPS".equals(item.getProdCode()) || "OHPS".equals(item.getProdCode())) {
                        a74.setRate(item.getTierRate0());
                        a74.setLargeMax(item.getTierMinAmt0());
                    } else {
                        switch (item.getNoOfTier() - 1) {
                        case 0:
                            a74.setRate(item.getTierRate0());
                            a74.setLargeMax(item.getMinAmtDep());
                            break;
                        case 1:
                            a74.setRate(item.getTierRate1());
                            a74.setLargeMax(item.getTierMinAmt1());
                            break;
                        case 2:
                            a74.setRate(item.getTierRate2());
                            a74.setLargeMax(item.getTierMinAmt2());
                            break;
                        case 3:
                            a74.setRate(item.getTierRate3());
                            a74.setLargeMax(item.getTierMinAmt3());
                            break;
                        case 4:
                            a74.setRate(item.getTierRate4());
                            a74.setLargeMax(item.getTierMinAmt4());
                            break;
                        case 5:
                            a74.setRate(item.getTierRate5());
                            a74.setLargeMax(item.getTierMinAmt5());
                            break;
                        case 6:
                            a74.setRate(item.getTierRate6());
                            a74.setLargeMax(item.getTierMinAmt6());
                            break;
                        case 7:
                            a74.setRate(item.getTierRate7());
                            a74.setLargeMax(item.getTierMinAmt7());
                            break;
                        case 8:
                            a74.setRate(item.getTierRate8());
                            a74.setLargeMax(item.getTierMinAmt8());
                            break;
                        case 9:
                            a74.setRate(item.getTierRate9());
                            a74.setLargeMax(item.getTierMinAmt9());
                            break;
                        }
                    }
                    // TODO
                }
            }
        }
        return a74;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
