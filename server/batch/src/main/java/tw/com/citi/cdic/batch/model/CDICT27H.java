package tw.com.citi.cdic.batch.model;

/**
 * @author Lancelot
 * @since 2010/10/7
 */
public class CDICT27H {
    private String sourceIndicator;
    private String transactionCode;
    private String description1;
    private String description2;
    private String stateMne;

    public void setSourceIndicator(String sourceIndicator) {
        this.sourceIndicator = sourceIndicator;
    }

    public String getSourceIndicator() {
        return sourceIndicator;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription2() {
        return description2;
    }

    public void setStateMne(String stateMne) {
        this.stateMne = stateMne;
    }

    public String getStateMne() {
        return stateMne;
    }
}
