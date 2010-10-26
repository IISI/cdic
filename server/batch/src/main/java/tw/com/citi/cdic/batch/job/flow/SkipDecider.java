package tw.com.citi.cdic.batch.job.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * 查詢 db 的資料來判斷是否要執行接下來的步驟，或是跳過接下來的步驟。
 * 如果要執行，則會回傳 "EXECUTE"；如果要跳過，則會回傳 "SKIP"。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class SkipDecider implements JobExecutionDecider {

    protected static final Logger logger = LoggerFactory.getLogger(SkipDecider.class);

    private String stepName;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution,
            StepExecution stepExecution) {
        // TODO 應該要根據 db 的資料來決定是要回傳 SKIP 或是 EXECUTE
//        if ("f01".equals(stepName)) {
//            return new FlowExecutionStatus("EXECUTE");
//        } else {
//            logger.info("skipping step [{}]", stepName);
//            return new FlowExecutionStatus("SKIP");
//        }
        return new FlowExecutionStatus("EXECUTE");
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

}
