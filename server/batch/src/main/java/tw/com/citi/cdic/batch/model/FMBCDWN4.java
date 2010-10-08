package tw.com.citi.cdic.batch.model;

/**
 * FMBCDWN4 Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class FMBCDWN4 {
    private String recType;
    private String filler1;
    private String acct;
    private String description;
    private String IBCode;
    private String filler2;

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getRecType() {
        return recType;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getFiller1() {
        return filler1;
    }

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

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getFiller2() {
        return filler2;
    }
}
