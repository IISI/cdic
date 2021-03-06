package tw.com.citi.cdic.batch.model;

/**
 * A76, CDICF24 Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class A76 {
    private String unit;
    private String branchNo;
    private String srNo;
    private String apNo;
    private String custId;
    private String type;
    private String startNo;
    private String endNo;
    private String currencyCode;
    private double amt;
    private String dueDate;
    private String code;
    private String dishonoredReason;
    private String entryDate;
    private String reserveDate;

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

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {
        return custId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }

    public String getStartNo() {
        return startNo;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }

    public String getEndNo() {
        return endNo;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public double getAmt() {
        return amt;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDishonoredReason(String dishonoredReason) {
        this.dishonoredReason = dishonoredReason;
    }

    public String getDishonoredReason() {
        return dishonoredReason;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setApNo(String apNo) {
        this.apNo = apNo;
    }

    public String getApNo() {
        return apNo;
    }
}
