package tw.com.citi.cdic.client.model;

import java.util.Date;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/22
 */
public class CDICFileSts {

    private String fileGroup;

    private String fileNo;

    private String subFile;

    private String filename;

    private String status;

    private String starter;

    private String confirmer;

    private Date confirmDateTime;

    private String executor;

    private Date executeDateTime;

    private String fileDesc;

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getSubFile() {
        return subFile;
    }

    public void setSubFile(String subFile) {
        this.subFile = subFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }

    public Date getConfirmDateTime() {
        return confirmDateTime;
    }

    public void setConfirmDateTime(Date confirmDateTime) {
        this.confirmDateTime = confirmDateTime;
    }

    public void setFileGroup(String fileGroup) {
        this.fileGroup = fileGroup;
    }

    public String getFileGroup() {
        return fileGroup;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecuteDateTime(Date executeDateTime) {
        this.executeDateTime = executeDateTime;
    }

    public Date getExecuteDateTime() {
        return executeDateTime;
    }

}
