package tw.com.citi.cdic.batch.model;

import java.util.Date;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public class A23 {

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

    private double stopPayAmount;

    private String jointCode;

    private String overdrawStatus;

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

    public double getStopPayAmount() {
        return stopPayAmount;
    }

    public void setStopPayAmount(double stopPayAmount) {
        this.stopPayAmount = stopPayAmount;
    }

    public String getJointCode() {
        return jointCode;
    }

    public void setJointCode(String jointCode) {
        this.jointCode = jointCode;
    }

    public String getOverdrawStatus() {
        return overdrawStatus;
    }

    public void setOverdrawStatus(String overdrawStatus) {
        this.overdrawStatus = overdrawStatus;
    }

    public Date getLastTxDate() {
        return lastTxDate;
    }

    public void setLastTxDate(Date lastTxDate) {
        this.lastTxDate = lastTxDate;
    }

}
