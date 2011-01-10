package tw.com.citi.cdic.client.dto;

public class BatchDto {
    public static enum BatchType {
        All, GROUP, FILE, REPORT
    }

    private boolean allowExecution;
    private String batchId;
    private String batchName;
    private BatchType batchType;
    private String status;
    private String sourceReady;
    private String sourceNotReady;
    private boolean hasGroup;

    public void setAllowExecution(boolean allowExecution) {
        this.allowExecution = allowExecution;
    }

    public boolean isAllowExecution() {
        return allowExecution;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchType(BatchType batchType) {
        this.batchType = batchType;
    }

    public BatchType getBatchType() {
        return batchType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setHasGroup(boolean hasGroup) {
        this.hasGroup = hasGroup;
    }

    public boolean isHasGroup() {
        return hasGroup;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setSourceReady(String sourceReady) {
        this.sourceReady = sourceReady;
    }

    public String getSourceReady() {
        return sourceReady;
    }

    public void setSourceNotReady(String sourceNotReady) {
        this.sourceNotReady = sourceNotReady;
    }

    public String getSourceNotReady() {
        return sourceNotReady;
    }
}
