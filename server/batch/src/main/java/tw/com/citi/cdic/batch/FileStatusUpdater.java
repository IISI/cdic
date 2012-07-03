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
import org.springframework.batch.core.annotation.AfterStep;

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

    @AfterStep
    public void updateStatus(StepExecution stepExecution) {
        String stepName = stepExecution.getStepName();
        logger.debug("record step status @AfterStep, step name = {}", stepName);
        stepName = stepName.toUpperCase().substring(0, 3);
        if (stepName.startsWith("F") || stepName.startsWith("T")) {
            ExitStatus status = stepExecution.getExitStatus();
            if (status == null) {
                logger.debug("record step status, step name = {}, status = {}", stepName, stepExecution.getExitStatus()
                        .getExitCode());
            } else {
                logger.debug("record step status, step name = {}, status = {}", stepName, stepExecution.getExitStatus()
                        .and(status).getExitCode());
            }
            CDICFileStatus fileStatus = this.CDICFileStatusDao.findByFileNo(stepName);
            if (ExitStatus.COMPLETED.compareTo(status) == 0) {
                fileStatus.setStatus("2");
            } else {
                fileStatus.setStatus("5");
            }
            this.CDICFileStatusDao.update(fileStatus);
            // F12A, F15A 處理
            if ("F12".equals(stepName) || "F15".equals(stepName)) {
                CDICFileStatus lcb = this.CDICFileStatusDao.findByFileNo(stepName + " LCB");
                lcb.setStatus(fileStatus.getStatus());
                this.CDICFileStatusDao.update(lcb);
            }
            logger.debug("update step status, step name = {}, status = {}", stepName, status.getExitCode());
        }
    }

    @AfterJob
    public void updateStatus(JobExecution jobExecution) {
        Map<String, ExitStatus> stepResults = new HashMap<String, ExitStatus>();
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        for (StepExecution stepExecution : stepExecutions) {
            String stepName = stepExecution.getStepName();
            logger.debug("record step status @AfterJob, step name = {}", stepName);
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
        Set<String> failFile = new HashSet<String>();

        for (Map.Entry<String, ExitStatus> entry : stepResults.entrySet()) {
            FileStep fileStep = FileStep.valueOf(entry.getKey());
            CDICFileStatus fileStatus = this.CDICFileStatusDao.findByFileNo(fileStep);
            if (ExitStatus.COMPLETED.compareTo(entry.getValue()) != 0) {
                failFile.add(fileStatus.getFileNo().trim());
                String group = fileStatus.getFileGroup();
                if (group != null && !"".equals(group.trim())) {
                    failGroup.add(group);
                }
            }
        }

        // Group處理，若fileStatus有Group，且ExitStatus不為COMPLETED，則，同Group的狀態都設為失敗
        for (String group : failGroup) {
            List<CDICFileStatus> files = this.CDICFileStatusDao.findByGroup(group);
            for (CDICFileStatus file : files) {
                file.setStatus("5");
                this.CDICFileStatusDao.update(file);
            }
        }
        boolean group1Fail = failFile.contains("F02") || failFile.contains("F03") || failFile.contains("F04")
                || failFile.contains("F08");
        Set<String> resetStatus = new HashSet<String>();
        // 1. 若 Group1 fail or F01 fail，reset F07's Status 為 '0'
        if (group1Fail || failFile.contains("F01")) {
            logger.debug("record step status, reset F07");
            resetStatus.add("F07");
        }
        // 2. 若 Group1 fail or F01 fail or F05 fail，reset F18's Status 為 '0'
        if (group1Fail || failFile.contains("F01") || failFile.contains("F05")) {
            resetStatus.add("F18");
        }
        // 3. 若 Group1 fail or F05 fail or F07 fail or F10 fail or F12 fail
        // or F24 fail，reset F99's Status 為 '0'
        if (group1Fail || failFile.contains("F05") || failFile.contains("F07") || failFile.contains("F10")
                || failFile.contains("F12") || failFile.contains("F24")) {
            resetStatus.add("F99");
        }
        if (!resetStatus.isEmpty()) {
            for (String reset : resetStatus) {
                CDICFileStatus file = this.CDICFileStatusDao.findByFileNo(reset);
                file.setStatus("0");
                this.CDICFileStatusDao.update(file);
            }
        }
    }

    public void setCDICFileStatusDao(CDICFileStatusDao cDICFileStatusDao) {
        CDICFileStatusDao = cDICFileStatusDao;
    }

}
