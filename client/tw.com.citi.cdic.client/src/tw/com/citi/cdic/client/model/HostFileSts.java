package tw.com.citi.cdic.client.model;

import java.util.Date;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/22
 */
public class HostFileSts {

    private String name;

    private int recLen;

    private Date hostDateTime;
    
    private String hostDateTimeFmt;

    private Date copyDateTime;
    
    private String copyDateTimeFmt;

    private String processUser;

    private String status;
    
    private int size;
    
    private int record;
    
    private String fileDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecLen() {
        return recLen;
    }

    public void setRecLen(int recLen) {
        this.recLen = recLen;
    }

    public Date getHostDateTime() {
        return hostDateTime;
    }

    public void setHostDateTime(Date hostDateTime) {
        this.hostDateTime = hostDateTime;
    }

    public Date getCopyDateTime() {
        return copyDateTime;
    }

    public void setCopyDateTime(Date copyDateTime) {
        this.copyDateTime = copyDateTime;
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

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setHostDateTimeFmt(String hostDateTimeFmt) {
        this.hostDateTimeFmt = hostDateTimeFmt;
    }

    public String getHostDateTimeFmt() {
        return hostDateTimeFmt;
    }

    public void setCopyDateTimeFmt(String copyDateTimeFmt) {
        this.copyDateTimeFmt = copyDateTimeFmt;
    }

    public String getCopyDateTimeFmt() {
        return copyDateTimeFmt;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getRecord() {
        return record;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileDesc() {
        return fileDesc;
    }

}
