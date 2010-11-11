package tw.com.citi.cdic.batch.step.tasklet;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;
import org.springframework.ui.jasperreports.JasperReportsUtils;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/9
 */
public class GenerateReportTasklet implements Tasklet {

    private Resource resource;

    @Override
    public RepeatStatus execute(StepContribution contribution,
            ChunkContext chunkContext) throws Exception {
        JasperReport report = JasperCompileManager.compileReport("report.jrxml");
        Map parameters = new HashMap();
        JRDataSource reportData = JasperReportsUtils.convertReportData(null);
        File file = resource.getFile();
        FileOutputStream stream = new FileOutputStream(file, false);
        JasperReportsUtils.renderAsPdf(report, parameters, reportData, stream);
        return RepeatStatus.FINISHED;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
