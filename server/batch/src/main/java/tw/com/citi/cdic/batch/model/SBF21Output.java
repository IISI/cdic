package tw.com.citi.cdic.batch.model;

public class SBF21Output {
    public enum TYPE {
        A73, B73, C73
    }

    private TYPE type;

    private A73 record;

    private boolean writeSample;

    public void setType(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public void setRecord(A73 record) {
        this.record = record;
    }

    public A73 getRecord() {
        return record;
    }

    public void setWriteSample(boolean writeSample) {
        this.writeSample = writeSample;
    }

    public boolean isWriteSample() {
        return writeSample;
    }
}
