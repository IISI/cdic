package tw.com.citi.cdic.client.model;

import java.util.Date;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/22
 */
public class TableFlow {

    private int CDICFileStatus;

    private Date custDate;

    private String starter;

    private String initUserId;

    private Date initDateTime;

    private String initStatus;

    public int getCDICFileStatus() {
        return CDICFileStatus;
    }

    public void setCDICFileStatus(int cDICFileStatus) {
        CDICFileStatus = cDICFileStatus;
    }

    public Date getCustDate() {
        return custDate;
    }

    public void setCustDate(Date custDate) {
        this.custDate = custDate;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getInitUserId() {
        return initUserId;
    }

    public void setInitUserId(String initUserId) {
        this.initUserId = initUserId;
    }

    public Date getInitDateTime() {
        return initDateTime;
    }

    public void setInitDateTime(Date initDateTime) {
        this.initDateTime = initDateTime;
    }

    public String getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(String initStatus) {
        this.initStatus = initStatus;
    }

}
