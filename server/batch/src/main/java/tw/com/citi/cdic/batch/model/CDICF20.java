package tw.com.citi.cdic.batch.model;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/19
 */
public class CDICF20 {

    private String unit;

    private String branchNo;

    private String currencyCode;

    private String rateDate;

    private double transRate;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public double getTransRate() {
        return transRate;
    }

    public void setTransRate(double transRate) {
        this.transRate = transRate;
    }

}
