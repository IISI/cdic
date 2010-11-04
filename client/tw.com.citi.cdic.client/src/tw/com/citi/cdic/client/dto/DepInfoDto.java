package tw.com.citi.cdic.client.dto;

import java.util.Date;

public class DepInfoDto {
    private String name;
    private String depType;
    private Date lastDateTime;
    private String user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setLastDateTime(Date lastDateTime) {
        this.lastDateTime = lastDateTime;
    }

    public Date getLastDateTime() {
        return lastDateTime;
    }

    public void setDepType(String depType) {
        this.depType = depType;
    }

    public String getDepType() {
        return depType;
    }
}
