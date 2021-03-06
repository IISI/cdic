package tw.com.citi.cdic.batch.model;

/**
 * A35, B35, C35, CDICF11 Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class A35 {
    private String unit;
    private String caseNo;
    private String caseName;
    private int dpisDate;
    private int dueDate;
    private String currencyCode;
    private double cryAmt;
    private String classType;
    private String protect;
    private boolean writeSample;

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setDpisDate(int dpisDate) {
        this.dpisDate = dpisDate;
    }

    public int getDpisDate() {
        return dpisDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCryAmt(double cryAmt) {
        this.cryAmt = cryAmt;
    }

    public double getCryAmt() {
        return cryAmt;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassType() {
        return classType;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public String getProtect() {
        return protect;
    }

    public void setWriteSample(boolean writeSample) {
        this.writeSample = writeSample;
    }

    public boolean isWriteSample() {
        return writeSample;
    }
}
