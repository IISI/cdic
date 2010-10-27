package tw.com.citi.cdic.batch.model;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBF11Output {

    private A35 a35;

    private boolean writeSample;

    public boolean isWriteSample() {
        return writeSample;
    }

    public void setWriteSample(boolean writeSample) {
        this.writeSample = writeSample;
    }

    public void setA35(A35 a35) {
        this.a35 = a35;
    }

    public A35 getA35() {
        return a35;
    }

}
