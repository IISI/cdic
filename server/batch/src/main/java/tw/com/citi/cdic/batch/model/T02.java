package tw.com.citi.cdic.batch.model;

public class T02 {
    private String acct;
    private String IBCode;
    private String description;

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getAcct() {
        return acct;
    }

    public void setIBCode(String iBCode) {
        IBCode = iBCode;
    }

    public String getIBCode() {
        return IBCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
