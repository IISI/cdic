package tw.com.citi.cdic.batch.model;

import java.util.Date;

/**
 * A22, B22, C22 共用的 domain 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public class A22 {

    private String unit;

    private String branchNo;

    private String srNo;

    private String apNo;

    private String charCode;

    private String status;

    private String customerId;

    private String customerIdNo;

    private String customerType;

    private String slipNo;

    private String currencyCode;

    private double amount;

    private double stopPayAmount;

    private Date beginDate;

    private Date dueDate;

    private String rateType;

    private String period;

    private String intType;

    private String nameCode;

    private double intRate;

    private String intPayCode;

    private String autoPrim;

    private String autoIntNo;

    private Date issueDate;

    private Date reIssueDate;

    private String gsAccountCode;

    private String jointCode;

    private String sdCase;

    private Date intEndDate;

    private double intPay;

    private double intPayable;

    private double violateAmount;

    private String pgKind;

    private double pgAmount;

    private Date pgSetDate;

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

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getStopPayAmount() {
        return stopPayAmount;
    }

    public void setStopPayAmount(double stopPayAmount) {
        this.stopPayAmount = stopPayAmount;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getIntType() {
        return intType;
    }

    public void setIntType(String intType) {
        this.intType = intType;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public double getIntRate() {
        return intRate;
    }

    public void setIntRate(double intRate) {
        this.intRate = intRate;
    }

    public String getIntPayCode() {
        return intPayCode;
    }

    public void setIntPayCode(String intPayCode) {
        this.intPayCode = intPayCode;
    }

    public String getAutoPrim() {
        return autoPrim;
    }

    public void setAutoPrim(String autoPrim) {
        this.autoPrim = autoPrim;
    }

    public String getAutoIntNo() {
        return autoIntNo;
    }

    public void setAutoIntNo(String autoIntNo) {
        this.autoIntNo = autoIntNo;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReIssueDate() {
        return reIssueDate;
    }

    public void setReIssueDate(Date reIssueDate) {
        this.reIssueDate = reIssueDate;
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

    public String getSdCase() {
        return sdCase;
    }

    public void setSdCase(String sdCase) {
        this.sdCase = sdCase;
    }

    public Date getIntEndDate() {
        return intEndDate;
    }

    public void setIntEndDate(Date intEndDate) {
        this.intEndDate = intEndDate;
    }

    public double getIntPay() {
        return intPay;
    }

    public void setIntPay(double intPay) {
        this.intPay = intPay;
    }

    public double getIntPayable() {
        return intPayable;
    }

    public void setIntPayable(double intPayable) {
        this.intPayable = intPayable;
    }

    public double getViolateAmount() {
        return violateAmount;
    }

    public void setViolateAmount(double violateAmount) {
        this.violateAmount = violateAmount;
    }

    public String getPgKind() {
        return pgKind;
    }

    public void setPgKind(String pgKind) {
        this.pgKind = pgKind;
    }

    public double getPgAmount() {
        return pgAmount;
    }

    public void setPgAmount(double pgAmount) {
        this.pgAmount = pgAmount;
    }

    public Date getPgSetDate() {
        return pgSetDate;
    }

    public void setPgSetDate(Date pgSetDate) {
        this.pgSetDate = pgSetDate;
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
