package tw.com.citi.cdic.batch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterJob;

import tw.com.citi.cdic.batch.dao.CDICFileStatusDao;
import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * 批次執行完畢之後，根據每一個 step 的狀態，更新 CDICFileStatus table 的資料。
 * 
 * @author Chih-Liang Chang
 * @since 2010/11/17
 */
public class FileStatusUpdater {

    protected static final Logger logger = LoggerFactory.getLogger(FileStatusUpdater.class);

    private CDICFileStatusDao CDICFileStatusDao;

    @AfterJob
    public void updateStatus(JobExecution jobExecution) {
        Map<String, ExitStatus> stepResults = new HashMap<String, ExitStatus>();
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        for (StepExecution stepExecution : stepExecutions) {
            String stepName = stepExecution.getStepName();
            stepName = stepName.toUpperCase().substring(0, 3);
            if (stepName.startsWith("F") || stepName.startsWith("T")) {
                ExitStatus status = stepResults.get(stepName);
                if (status == null) {
                    stepResults.put(stepName, stepExecution.getExitStatus());
                } else {
                    stepResults.put(stepName, stepExecution.getExitStatus().and(status));
                }
            }
        }
        
        for (Map.Entry<String, ExitStatus> entry : stepResults.entrySet()) {
            FileStep fileStep = FileStep.valueOf(entry.getKey());
            CDICFileStatus fileStatus = this.CDICFileStatusDao.findByFileNo(fileStep);
            if (ExitStatus.COMPLETED == entry.getValue()) {
                fileStatus.setStatus("2");
            } else {
                fileStatus.setStatus("5");
            }
            this.CDICFileStatusDao.update(fileStatus);
        }
    }

    public void setCDICFileStatusDao(CDICFileStatusDao cDICFileStatusDao) {
        CDICFileStatusDao = cDICFileStatusDao;
    }

}
