package tw.com.citi.cdic.batch.model;

import java.util.Date;

/**
 * A21, B21, C21 共用的 domain 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/4
 */
public class A21 {

    private String unit;

    private String branchNo;

    private String srNo;

    private String apNo;

    private String charCode;

    private String status;

    private String customerId;

    private String customerIdNo;

    private String customerType;

    private Date openDate;

    private String currencyCode;

    private double accountBalance;

    private double balance;

    private double stopPayAmount;

    private double cardAmount;

    private String gsAccountCode;

    private String jointCode;

    private String rateType;

    private double intRate;

    private double intPayable;

    private String overdrawStatus;

    private String taxCode;

    private double grossInt;

    private double grossTax;

    private Date lastTxDate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getStopPayAmount() {
        return stopPayAmount;
    }

    public void setStopPayAmount(double stopPayAmount) {
        this.stopPayAmount = stopPayAmount;
    }

    public double getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(double cardAmount) {
        this.cardAmount = cardAmount;
    }

    public String getGsAccountCode() {
        return gsAccountCode;
    }

    public void setGsAccountCode(String gsAccountCode) {
        this.gsAccountCode = gsAccountCode;
    }

    public String getJointCode() {
        return jointCode;
    }

    public void setJointCode(String jointCode) {
        this.jointCode = jointCode;
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

    public String getOverdrawStatus() {
        return overdrawStatus;
    }

    public void setOverdrawStatus(String overdrawStatus) {
        this.overdrawStatus = overdrawStatus;
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

    public Date getLastTxDate() {
        return lastTxDate;
    }

    public void setLastTxDate(Date lastTxDate) {
        this.lastTxDate = lastTxDate;
    }

}
