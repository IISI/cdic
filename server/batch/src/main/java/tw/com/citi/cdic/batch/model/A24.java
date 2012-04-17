package tw.com.citi.cdic.batch.model;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/13
 */
public class A24 {

    public enum Type {

        A, B, C

    }

    private String unit;

    private String branchNo;

    private String controlSrNo;

    private String srNo;

    private String apNo;

    private String charCode;

    private String customerId;

    private String customerIdNo;

    private String customerName;

    private String customerBusinessCode;

    private String customerType;

    private String currencyCode;

    private double balance;

    private String rateType;

    private double intRate;

    private double intPayable;

    private String taxCode;

    private double grossInt;

    private double grossTax;

    private String originalAddress;

    private String address;

    private String tel1;

    private String tel2;

    private transient Type type;

    private boolean sample;

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

    public String getControlSrNo() {
        return controlSrNo;
    }

    public void setControlSrNo(String controlSrNo) {
        this.controlSrNo = controlSrNo;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getApNo() {
        return apNo;
    }

    public void setApNo(String apNo) {
        this.apNo = apNo;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBusinessCode() {
        return customerBusinessCode;
    }

    public void setCustomerBusinessCode(String customerBusinessCode) {
        this.customerBusinessCode = customerBusinessCode;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public double getIntRate() {
        return intRate;
    }

    public void setIntRate(double intRate) {
        this.intRate = intRate;
    }

    public double getIntPayable() {
        return intPayable;
    }

    public void setIntPayable(double intPayable) {
        this.intPayable = intPayable;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public double getGrossInt() {
        return grossInt;
    }

    public void setGrossInt(double grossInt) {
        this.grossInt = grossInt;
    }

    public double getGrossTax() {
        return grossTax;
    }

    public void setGrossTax(double grossTax) {
        this.grossTax = grossTax;
    }

    public String getOriginalAddress() {
        return originalAddress;
    }

    public void setOriginalAddress(String originalAddress) {
        this.originalAddress = originalAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public boolean isSample() {
        return sample;
    }

    public void setSample(boolean sample) {
        this.sample = sample;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
