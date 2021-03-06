package tw.com.citi.cdic.client.model;

import java.util.Date;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/22
 */
public class LocalFileSts {

    private String name;

    private Date uploadDateTime;

    private String processUser;

    private int columnCount;

    private String status;

    private boolean exist;

    private String fileDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean isExist() {
        return exist;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileDesc() {
        return fileDesc;
    }

}
