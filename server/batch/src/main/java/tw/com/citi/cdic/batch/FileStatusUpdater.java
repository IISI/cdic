package tw.com.citi.cdic.batch;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                    logger.debug("record step status, step name = {}, status = {}", stepName, stepExecution
                            .getExitStatus().getExitCode());
                } else {
                    stepResults.put(stepName, stepExecution.getExitStatus().and(status));
                    logger.debug("record step status, step name = {}, status = {}", stepName, stepExecution
                            .getExitStatus().and(status).getExitCode());
                }
            }
        }

        Set<String> failGroup = new HashSet<String>();
        
        for (Map.Entry<String, ExitStatus> entry : stepResults.entrySet()) {
            FileStep fileStep = FileStep.valueOf(entry.getKey());
            CDICFileStatus fileStatus = this.CDICFileStatusDao.findByFileNo(fileStep);
            if (ExitStatus.COMPLETED.compareTo(entry.getValue()) == 0) {
                fileStatus.setStatus("2");
            } else {
                fileStatus.setStatus("5");
                String group = fileStatus.getFileGroup();
                if (group != null && !"".equals(group.trim())) {
                    failGroup.add(group);
                }
            }
            this.CDICFileStatusDao.update(fileStatus);
            logger.debug("update step status, step name = {}, status = {}", entry.getKey(), entry.getValue()
                    .getExitCode());
            // Group處理，若fileStatus有Group，且ExitStatus不為COMPLETED，則，同Group的狀態都設為失敗
            for (String group : failGroup) {
                List<CDICFileStatus> files = this.CDICFileStatusDao.findByGroup(group);
                for (CDICFileStatus file : files) {
                    file.setStatus("5");
                    this.CDICFileStatusDao.update(file);
                }
            }
        }
    }

    public void setCDICFileStatusDao(CDICFileStatusDao cDICFileStatusDao) {
        CDICFileStatusDao = cDICFileStatusDao;
    }

}
