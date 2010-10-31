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

}
