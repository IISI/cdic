package tw.com.citi.cdic.batch.model;

/**
 * A74, CDICF22I, CDICF22R, CDICF22L Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class A74 {
    private String unit;
    private String branchNo;
    private String currencyCode;
    private String rateType;
    private String type;
    private String period;
    private int largeMax;
    private String effectiveDate;
    private double rate;
    private boolean writeSample;
    private String key;

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getRateType() {
        return rateType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setLargeMax(int largeMax) {
        this.largeMax = largeMax;
    }

    public int getLargeMax() {
        return largeMax;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setWriteSample(boolean writeSample) {
        this.writeSample = writeSample;
    }

    public boolean isWriteSample() {
        return writeSample;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        key = currencyCode + rateType + type + period + effectiveDate + String.valueOf(largeMax);
        return key;
    }
}
