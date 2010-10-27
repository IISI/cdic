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

    private Date copyDateTime;

    private String processUser;

    private String status;

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

}
