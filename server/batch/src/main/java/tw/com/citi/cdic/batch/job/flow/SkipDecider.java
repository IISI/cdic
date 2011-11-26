package tw.com.citi.cdic.batch.job.flow;

import java.util.Arrays;
import java.util.List;

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
 * 查詢 db 的資料來判斷是否要執行接下來的步驟，或是跳過接下來的步驟。 如果要執行，則會回傳 "EXECUTE"，並將 status 更新成 4 執行中；
 * 如果要跳過，則會回傳 "SKIP"。
 * 
 * @author Chih-Liang Chang
 * @since 2010/9/29
 */
public class SkipDecider implements JobExecutionDecider {

    protected static final Logger logger = LoggerFactory.getLogger(SkipDecider.class);

    private FileStep stepName;

    private CDICFileStatusDao CDICFileStatusDao;

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        // 根據 db 的資料來決定是要回傳 SKIP 或是 EXECUTE
        if ("Group1".equals(stepName.name())) {
            CDICFileStatus f02Status = CDICFileStatusDao.findByFileNo("F02");
            CDICFileStatus f03Status = CDICFileStatusDao.findByFileNo("F03");
            CDICFileStatus f04Status = CDICFileStatusDao.findByFileNo("F04");
            CDICFileStatus f08Status = CDICFileStatusDao.findByFileNo("F08");
            if ("1".equals(f02Status.getStatus()) && "1".equals(f03Status.getStatus())
                    && "1".equals(f04Status.getStatus()) && "1".equals(f08Status.getStatus())) {
                return new FlowExecutionStatus("EXECUTE");
            } else {
                return new FlowExecutionStatus("SKIP");
            }
        } else {
            String[] s = new String[] { "2", "3" };
            List<String> allowSts = Arrays.asList(s);
            String f01Status = CDICFileStatusDao.findByFileNo("F01").getStatus();
            String f02Status = CDICFileStatusDao.findByFileNo("F02").getStatus();
            String f03Status = CDICFileStatusDao.findByFileNo("F03").getStatus();
            String f04Status = CDICFileStatusDao.findByFileNo("F04").getStatus();
            String f05Status = CDICFileStatusDao.findByFileNo("F05").getStatus();
            String f07Status = CDICFileStatusDao.findByFileNo("F07").getStatus();
            String f10Status = CDICFileStatusDao.findByFileNo("F10").getStatus();
            String f12Status = CDICFileStatusDao.findByFileNo("F12").getStatus();
            String f24Status = CDICFileStatusDao.findByFileNo("F24").getStatus();
            CDICFileStatus fileStatus = CDICFileStatusDao.findByFileNo(stepName);
            if ("1".equals(fileStatus.getStatus())) {
                // F07: F02/F03/F04
                if ("F07".equals(stepName.name())
                        && (!allowSts.contains(f02Status) || !allowSts.contains(f03Status) || !allowSts
                                .contains(f04Status))) {
                    return new FlowExecutionStatus("SKIP");
                }
                // F18: F01/F02/F03/F04/F05
                if ("F18".equals(stepName.name())
                        && (!allowSts.contains(f01Status) || !allowSts.contains(f02Status)
                                || !allowSts.contains(f03Status) || !allowSts.contains(f04Status) || !allowSts
                                .contains(f05Status))) {
                    return new FlowExecutionStatus("SKIP");
                }
                // F99: F02/F03/F04/F05/F07/F10/F12/F24
                if ("F99".equals(stepName.name())
                        && (!allowSts.contains(f02Status) || !allowSts.contains(f03Status)
                                || !allowSts.contains(f04Status) || !allowSts.contains(f05Status)
                                || !allowSts.contains(f07Status) || !allowSts.contains(f10Status)
                                || !allowSts.contains(f12Status) || !allowSts.contains(f24Status))) {
                    return new FlowExecutionStatus("SKIP");
                }
                fileStatus.setStatus("4");
                CDICFileStatusDao.update(fileStatus);
                return new FlowExecutionStatus("EXECUTE");
            } else {
                return new FlowExecutionStatus("SKIP");
            }
        }
    }

    public void setStepName(FileStep stepName) {
        this.stepName = stepName;
    }

    public void setCDICFileStatusDao(CDICFileStatusDao cDICFileStatusDao) {
        CDICFileStatusDao = cDICFileStatusDao;
    }

}
