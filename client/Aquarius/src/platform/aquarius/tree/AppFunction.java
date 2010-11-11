package platform.aquarius.tree;

public class AppFunction {

    private String functionId;

    private boolean addRight;

    private boolean changeRight;

    private boolean deleteRight;

    private boolean inqueryRight;

    private boolean executeRight;

    private boolean primaryRight;

    private String subMenu;

    private String itemSeq;

    private String functionName;

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public boolean isAddRight() {
        return addRight;
    }

    public void setAddRight(boolean addRight) {
        this.addRight = addRight;
    }

    public boolean isChangeRight() {
        return changeRight;
    }

    public void setChangeRight(boolean changeRight) {
        this.changeRight = changeRight;
    }

    public boolean isDeleteRight() {
        return deleteRight;
    }

    public void setDeleteRight(boolean deleteRight) {
        this.deleteRight = deleteRight;
    }

    public boolean isInqueryRight() {
        return inqueryRight;
    }

    public void setInqueryRight(boolean inqueryRight) {
        this.inqueryRight = inqueryRight;
    }

    public boolean isExecuteRight() {
        return executeRight;
    }

    public void setExecuteRight(boolean executeRight) {
        this.executeRight = executeRight;
    }

    public boolean isPrimaryRight() {
        return primaryRight;
    }

    public void setPrimaryRight(boolean primaryRight) {
        this.primaryRight = primaryRight;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public String getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(String itemSeq) {
        this.itemSeq = itemSeq;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

}
