package tw.com.citi.cdic.client.dto;

/**
 * 
 * @author Lancelot
 * @since 2010/10/31
 */
public class ConfirmDto {
    private String group;

    private String fileSet;

    private String status;

    private String confirmer;

    private String fileNo;

    private String confirmDateTime;

    private String fileDesc;

    private String statusShow;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFileSet() {
        return fileSet;
    }

    public void setFileSet(String fileSet) {
        this.fileSet = fileSet;
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

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setConfirmDateTime(String confirmDateTime) {
        this.confirmDateTime = confirmDateTime;
    }

    public String getConfirmDateTime() {
        return confirmDateTime;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setStatusShow(String statusShow) {
        this.statusShow = statusShow;
    }

    public String getStatusShow() {
        return statusShow;
    }

}
