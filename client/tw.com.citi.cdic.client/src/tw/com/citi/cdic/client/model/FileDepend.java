package tw.com.citi.cdic.client.model;

public class FileDepend {
    public static enum DepType {
        LOCAL, HOST, CDIC
    }

    private String name;
    private String dependency;
    private String depType;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDepType(String depType) {
        this.depType = depType;
    }

    public String getDepType() {
        return depType;
    }
}
