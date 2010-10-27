package platform.aquarius.embedserver.pages;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.wicket.PageParameters;
import org.apache.wicket.request.target.resource.ResourceStreamRequestTarget;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;

import com.lowagie.text.pdf.PdfWriter;

/**
 * 
 * <p>
 * Abstract產生PDF報表.
 * </p>
 * 
 * <pre>
 * $Date: 2010-02-05 11:31:32 +0800 (週五, 05 二月 2010) $
 * $Author: iristu $
 * $Revision: 1537 $
 * $HeadURL: http://192.168.11.86/svn/branches/dev/rps/customize/src/main/java/com/mega/pages/AbstractPdfReportPage.java $
 * </pre>
 * 
 * @author TonyWang
 * @version $Revision: 1537 $
 * @version <ul>
 *          <li>2010/2/5,iristu,modify
 *          </ul>
 */
public abstract class AbstractPdfReportPage extends AbstractAquariusPage {

    /** The allow copy. */
    private boolean allowCopy = true;

    /** The allow print. */
    private boolean allowPrint = true;

    /**
     * <p>
     * 產生報表
     * </p>
     * 
     * <pre>
     * $Date: 2010-02-05 11:31:32 +0800 (週五, 05 二月 2010) $
     * $Author: iristu $
     * $Revision: 1537 $
     * $HeadURL: http://192.168.11.86/svn/branches/dev/rps/customize/src/main/java/com/mega/pages/AbstractPdfReportPage.java $
     * </pre>
     * 
     * @author TonyWang
     * @version $Revision: 1537 $
     */
    private class ReportOutputStream extends AbstractResourceStreamWriter {

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 1L;

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.apache.wicket.util.resource.IResourceStreamWriter#write(java.
         * io.OutputStream)
         */
        public void write(OutputStream output) {

            try {
                // 取得靜態參數
                Map<String, Object> para = getReportParameter();

                // 取得動態報表資料集
                JRDataSource ds = getReportDataSource();

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        getReportDefinition(), para, ds);

                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                        jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);

                setDefaultPdfParameter(exporter);
                setPdfPermission(exporter);

                exporter.exportReport();

            } catch (Throwable e) {
                logError(e.getMessage(), e);
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.apache.wicket.util.resource.IResourceStream#getContentType()
         */
        public String getContentType() {
            return "application/pdf";
        }

    }

    /**
     * 取得圖檔的inputStream
     * 
     * @param path
     *            檔案名稱
     * @return InputStream
     */
    protected InputStream getInputStream(String fileName) {
        return getClass().getResourceAsStream("/reports/" + fileName);
    }

    /**
     * 取得報表
     * 
     * @param reportName
     *            報表名稱
     * @return JasperReport
     */
    protected JasperReport getReport(String reportName) {
        try {
            return (JasperReport) JRLoader.loadObject(getClass()
                    .getResourceAsStream("/reports/" + reportName));
        } catch (JRException e) {
            return null;
        }
    }

    /**
     * Sets the default pdf parameter.
     * 
     * @param exporter
     *            the new default pdf parameter
     */
    private void setDefaultPdfParameter(JRPdfExporter exporter) {
        exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
        exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,
                Boolean.TRUE);
        exporter.setParameter(JRPdfExporterParameter.PDF_VERSION,
                JRPdfExporterParameter.PDF_VERSION_1_7);
    }

    /**
     * Sets the pdf permission.
     * 
     * @param exporter
     *            the new pdf permission
     */
    public void setPdfPermission(JRPdfExporter exporter) {
        int opt = 0;
        if (allowCopy) {
            opt = opt | PdfWriter.ALLOW_COPY;
        }

        if (allowPrint) {
            opt = opt | PdfWriter.ALLOW_PRINTING;
        }
        exporter.setParameter(JRPdfExporterParameter.PERMISSIONS,
                Integer.valueOf(opt));
    }

    public AbstractPdfReportPage() {

    }

    /**
     * Instantiates a new json pdf report page.
     * 
     * @param para
     *            the para
     */
    public AbstractPdfReportPage(PageParameters para) {
        super(para);
    }

    @Override
    public void processRequest(boolean isAjax) throws Exception {
        super.processRequest(isAjax);
        execute();
    }

    public void execute() {
        getRequestCycle().setRequestTarget(
                new ResourceStreamRequestTarget(new ReportOutputStream()));
    }

    /**
     * Gets the report parameter.
     * 
     * @return the report parameter
     */
    public abstract Map<String, Object> getReportParameter();

    /**
     * Gets the report data source.
     * 
     * @return the report data source
     */
    public abstract JRDataSource getReportDataSource();

    /**
     * Gets the report definition.
     * 
     * @return the report definition
     */
    public abstract InputStream getReportDefinition();

    /**
     * get the user info
     * 
     * @return user info
     */
    // public MegaSSOUserDetails getUserInfo() {
    //
    // Authentication auth = SecurityContextHolder.getContext()
    // .getAuthentication();
    //
    // return (MegaSSOUserDetails) auth.getPrincipal();
    // }

}
