package tw.com.citi.cdic.batch.model;

/**
 * SBF08 input local file JointAcclistLocal
 * 
 * @author Lancelot
 * @since 2010/10/7
 */
public class JointAcclist {
    private double number;
    private String GRB;
    private String branchNo;
    private String account;

    public void setNumber(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setGRB(String gRB) {
        GRB = gRB;
    }

    public String getGRB() {
        return GRB;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
