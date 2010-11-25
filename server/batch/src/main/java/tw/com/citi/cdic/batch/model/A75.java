/**
 * 
 */
package tw.com.citi.cdic.batch.model;

/**
 * A75, CDICF23 Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class A75 {
    private String unit;
    private String branchNo;
    private String date;
    private String srNo;
    private String dueDate;
    private String defferDate;
    private String payBank;
    private String billType;
    private String billNo;
    private String currencyCode;
    private double billAmt;
    private String billDrawerAcctNo;
    private String billDepAcctNo;
    private String billStatus;

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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDefferDate(String defferDate) {
        this.defferDate = defferDate;
    }

    public String getDefferDate() {
        return defferDate;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setBillAmt(double billAmt) {
        this.billAmt = billAmt;
    }

    public double getBillAmt() {
        return billAmt;
    }

    public void setBillDrawerAcctNo(String billDrawerAcctNo) {
        this.billDrawerAcctNo = billDrawerAcctNo;
    }

    public String getBillDrawerAcctNo() {
        return billDrawerAcctNo;
    }

    public void setBillDepAcctNo(String billDepAcctNo) {
        this.billDepAcctNo = billDepAcctNo;
    }

    public String getBillDepAcctNo() {
        return billDepAcctNo;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillStatus() {
        return billStatus;
    }
}
