package tw.com.citi.cdic.batch.model;

import java.util.Date;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/8
 */
public class CDICFileStatus {

    private String fileNo;

    private String fileGroup;

    private String subFile;

    private String filename;

    private String status;

    private String confirmer;

    private Date confirmDateTime;

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(String fileGroup) {
        this.fileGroup = fileGroup;
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

}
