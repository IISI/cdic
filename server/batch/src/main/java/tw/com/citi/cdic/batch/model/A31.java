package tw.com.citi.cdic.batch.model;

/**
 * A31, B31 共用的 domain 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/13
 */
public class A31 {

    private String unit;

    private String branchNo;

    private String srNo;

    private String currencyCode;

    private String customerId;

    private String customerIdNo;

    private double locateRate;

    private String memo;

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

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public double getLocateRate() {
        return locateRate;
    }

    public void setLocateRate(double locateRate) {
        this.locateRate = locateRate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
