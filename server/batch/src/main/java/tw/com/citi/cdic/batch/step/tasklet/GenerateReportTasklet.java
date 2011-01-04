package tw.com.citi.cdic.batch.step.tasklet;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import tw.com.citi.cdic.batch.dao.ReportDao;
import tw.com.citi.cdic.batch.dao.TableFlowDao;
import tw.com.citi.cdic.batch.model.Report;
import tw.com.citi.cdic.batch.model.TableFlow;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/9
 */
public class GenerateReportTasklet implements Tasklet {

    private Resource resource;

    private TableFlowDao tableFlowDao;

    private ReportDao reportDao;

    @Override
    public RepeatStatus execute(StepContribution contribution,
            ChunkContext chunkContext) throws Exception {
        Resource reportTemplate = new ClassPathResource("report.jrxml");
        JasperReport report = JasperCompileManager.compileReport(reportTemplate.getInputStream());
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        TableFlow tableFlow = tableFlowDao.getTableFlow();
        parameters.put("baseDate", tableFlow.getCustDate());
        
        Map<JRExporterParameter, Object> jasperExporterParams = new HashMap<JRExporterParameter, Object>();
        jasperExporterParams.put(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        
        List<Report> data = reportDao.findAll();
        JRDataSource reportData = JasperReportsUtils.convertReportData(data);
        
        File file = resource.getFile();
        FileOutputStream stream = new FileOutputStream(file, false);
        JasperReportsUtils.renderAsXls(report, parameters, reportData, stream, jasperExporterParams);
        return RepeatStatus.FINISHED;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setTableFlowDao(TableFlowDao tableFlowDao) {
        this.tableFlowDao = tableFlowDao;
    }

    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

}
