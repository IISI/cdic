package tw.com.citi.cdic.batch.model;

/**
 * CDICT03G Layout
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class CDICT03G {
    private String systemId;
    private String productCode;
    private String description;

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
