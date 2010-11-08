package tw.com.citi.cdic.batch.job.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import tw.com.citi.cdic.batch.FileStep;
import tw.com.citi.cdic.batch.dao.CDICFileStatusDao;
import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * 查詢 db 的資料來判斷是否要執行接下來的步驟，或是跳過接下來的步驟。
 * 如果要執行，則會回傳 "EXECUTE"；如果要跳過，則會回傳 "SKIP"。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class SkipDecider implements JobExecutionDecider {

    protected static final Logger logger = LoggerFactory.getLogger(SkipDecider.class);

    private FileStep stepName;

    private CDICFileStatusDao CDICFileStatusDao;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution,
            StepExecution stepExecution) {
        // 根據 db 的資料來決定是要回傳 SKIP 或是 EXECUTE
        CDICFileStatus fileStatus = CDICFileStatusDao.findByFileNo(stepName);
        if ("1".equals(fileStatus.getStatus())) {
            return new FlowExecutionStatus("EXECUTE");
        } else {
            return new FlowExecutionStatus("SKIP");
        }
    }

    public void setStepName(FileStep stepName) {
        this.stepName = stepName;
    }

    public void setCDICFileStatusDao(CDICFileStatusDao cDICFileStatusDao) {
        CDICFileStatusDao = cDICFileStatusDao;
    }

}
