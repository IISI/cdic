package tw.com.citi.cdic.batch.model;

import org.apache.commons.lang.StringUtils;

public class CDICF01 {
    private String id;
    private String headId;
    private String CName;
    private String accountNo;
    private String birthday;
    private String ceoCode;
    private String ceoName;
    private String busCode;
    private String address;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public String getHeadId() {
        return headId;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return StringUtils.leftPad(accountNo, 10, "0");
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setCeoCode(String ceoCode) {
        this.ceoCode = ceoCode;
    }

    public String getCeoCode() {
        return ceoCode;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCName(String cName) {
        CName = cName;
    }

    public String getCName() {
        return CName;
    }
}
