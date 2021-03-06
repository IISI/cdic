package tw.com.citi.cdic.batch.model;

/**
 * A73, B73, C73, CDICF21I, CDICF21R Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class A73 {
    private String unit;
    private String branchNo;
    private String srNo;
    private String depositReceiptNo;
    private String txnDate;
    private String serNo;
    private String txnReason;
    private String currencyCode;
    private double holdAmt;
    private String memo;
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

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setDepositReceiptNo(String depositReceiptNo) {
        this.depositReceiptNo = depositReceiptNo;
    }

    public String getDepositReceiptNo() {
        return depositReceiptNo;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setSerNo(String serNo) {
        this.serNo = serNo;
    }

    public String getSerNo() {
        return serNo;
    }

    public void setTxnReason(String txnReason) {
        this.txnReason = txnReason;
    }

    public String getTxnReason() {
        return txnReason;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setHoldAmt(double holdAmt) {
        this.holdAmt = holdAmt;
    }

    public double getHoldAmt() {
        return holdAmt;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        key = srNo + depositReceiptNo + txnDate + serNo + currencyCode + txnReason + memo;
        return key;
    }
}
