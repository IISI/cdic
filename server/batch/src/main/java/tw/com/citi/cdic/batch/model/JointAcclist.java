package tw.com.citi.cdic.batch.model;

/**
 * SBF08 input local file JointAcclistLocal
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class JointAcclist {

    private String GRB;

    private String account;

    public void setGRB(String gRB) {
        GRB = gRB;
    }

    public String getGRB() {
        return GRB;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

}
