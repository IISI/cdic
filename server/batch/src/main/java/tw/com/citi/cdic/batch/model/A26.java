package tw.com.citi.cdic.batch.model;


/**
 * A26, B26, C26 共用的 domain 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/4
 */
public class A26 {

    public enum Type {

        A, B, C

    }

    private String unit;

    private String branchNo;

    private String apNo;

    private String srNo;

    private String customerId;

    private String customerIdNo;

    private String checkNo;

    private String currencyCode;

    private double paySav;

    private String intPayable;

    private String intPayMemo;

    private String company;

    private String rc;

    private String refNo;

    private transient Type type;

    private transient boolean sample;

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

    public String getApNo() {
        return apNo;
    }

    public void setApNo(String apNo) {
        this.apNo = apNo;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getPaySav() {
        return paySav;
    }

    public void setPaySav(double paySav) {
        this.paySav = paySav;
    }

    public String getIntPayable() {
        return intPayable;
    }

    public void setIntPayable(String intPayable) {
        this.intPayable = intPayable;
    }

    public String getIntPayMemo() {
        return intPayMemo;
    }

    public void setIntPayMemo(String intPayMemo) {
        this.intPayMemo = intPayMemo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isSample() {
        return sample;
    }

    public void setSample(boolean sample) {
        this.sample = sample;
    }

}
