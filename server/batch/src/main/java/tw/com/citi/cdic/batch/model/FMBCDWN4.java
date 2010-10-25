package tw.com.citi.cdic.batch.model;

/**
 * T02, FMBCDWN4 Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class FMBCDWN4 {
    private String recType;
    private String filler;
    private String acct;
    private String description;
    private String IBCode;

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getAcct() {
        return acct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIBCode(String iBCode) {
        IBCode = iBCode;
    }

    public String getIBCode() {
        return IBCode;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getRecType() {
        return recType;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getFiller() {
        return filler;
    }
}
