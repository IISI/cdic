package tw.com.citi.cdic.batch.model;

import java.util.Date;

/**
 * A26, B26, C26 共用的 domain 物件。
 * 
 * @author Chih-Liang Chang
 * @since 2010/10/4
 */
public class A26 {

    private String unit;

    private String branchNo;

    private String apNo;

    private String srNo;

    private String customerId;

    private String customerIdNo;

    private String checkNo;

    private String currencyCode;

    private double paySav;

    private Date intPayable;

    private String intPayMemo;

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

    public Date getIntPayable() {
        return intPayable;
    }

    public void setIntPayable(Date intPayable) {
        this.intPayable = intPayable;
    }

    public String getIntPayMemo() {
        return intPayMemo;
    }

    public void setIntPayMemo(String intPayMemo) {
        this.intPayMemo = intPayMemo;
    }

}
