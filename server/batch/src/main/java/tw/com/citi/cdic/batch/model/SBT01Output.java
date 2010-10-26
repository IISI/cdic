package tw.com.citi.cdic.batch.model;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBT01Output {

    private T01 t01;

    private boolean writeSample;

    public boolean isWriteSample() {
        return writeSample;
    }

    public void setWriteSample(boolean writeSample) {
        this.writeSample = writeSample;
    }

    public void setT01(T01 t01) {
        this.t01 = t01;
    }

    public T01 getT01() {
        return t01;
    }

}
