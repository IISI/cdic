package tw.com.citi.cdic.batch.model;

import java.util.Date;


/**
 * A77, B77, C77, CDICF25 Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class A77 {
    private String unit;
    private String branchNo;
    private String srNo;
    private Date txnDate;
    private String currencyCode;
    private double dbAmt;
    private double crAmt;
    private String memoTxn;
    private String memoAgent;
    private String alterMemo;

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

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setDbAmt(double dbAmt) {
        this.dbAmt = dbAmt;
    }

    public double getDbAmt() {
        return dbAmt;
    }

    public void setCrAmt(double crAmt) {
        this.crAmt = crAmt;
    }

    public double getCrAmt() {
        return crAmt;
    }

    public void setMemoTxn(String memoTxn) {
        this.memoTxn = memoTxn;
    }

    public String getMemoTxn() {
        return memoTxn;
    }

    public void setMemoAgent(String memoAgent) {
        this.memoAgent = memoAgent;
    }

    public String getMemoAgent() {
        return memoAgent;
    }

    public void setAlterMemo(String alterMemo) {
        this.alterMemo = alterMemo;
    }

    public String getAlterMemo() {
        return alterMemo;
    }
}
