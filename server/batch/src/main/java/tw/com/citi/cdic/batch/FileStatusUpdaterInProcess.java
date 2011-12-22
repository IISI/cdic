package tw.com.citi.cdic.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;

import tw.com.citi.cdic.batch.dao.CDICFileStatusDao;
import tw.com.citi.cdic.batch.model.CDICFileStatus;

/**
 * 處理非最後一個 step 的狀態更新
 * 
 * @author Yunglin Liu
 * @since 2011/03/21
 */
public class FileStatusUpdaterInProcess {

    protected static final Logger logger = LoggerFactory.getLogger(FileStatusUpdaterInProcess.class);

    private CDICFileStatusDao CDICFileStatusDao;

    @AfterStep
    public void updateStatus(StepExecution stepExecution) {
        String stepName = stepExecution.getStepName();
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
            if (ExitStatus.COMPLETED.compareTo(status) != 0) {
                CDICFileStatus fileStatus = this.CDICFileStatusDao.findByFileNo(stepName);
                fileStatus.setStatus("5");
                this.CDICFileStatusDao.update(fileStatus);
                if ("F12".equals(stepName) || "F15".equals(stepName)) {
                    CDICFileStatus lcb = this.CDICFileStatusDao.findByFileNo(stepName + " LCB");
                    lcb.setStatus(fileStatus.getStatus());
                    this.CDICFileStatusDao.update(lcb);
                }
                logger.debug("update step status, step name = {}, status = {}", stepName, status.getExitCode());
            }
        }
    }

    public void setCDICFileStatusDao(CDICFileStatusDao cDICFileStatusDao) {
        CDICFileStatusDao = cDICFileStatusDao;
    }

}
