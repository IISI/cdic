package tw.com.citi.cdic.batch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/15
 */
public class SBF18Output {

    private A61 a61;

    private List<A21> a21List = new ArrayList<A21>();

    private List<A21> b21List = new ArrayList<A21>();

    private List<A21> c21List = new ArrayList<A21>();

    private List<A22> a22List = new ArrayList<A22>();

    private List<A22> b22List = new ArrayList<A22>();

    private List<A22> c22List = new ArrayList<A22>();

    private List<A23> a23List = new ArrayList<A23>();

    private boolean writeSample;

    public A61 getA61() {
        return a61;
    }

    public void setA61(A61 a61) {
        this.a61 = a61;
    }

    public List<A21> getA21List() {
        return a21List;
    }

    public void setA21List(List<A21> a21List) {
        this.a21List = a21List;
    }

    public List<A21> getB21List() {
        return b21List;
    }

    public void setB21List(List<A21> b21List) {
        this.b21List = b21List;
    }

    public List<A21> getC21List() {
        return c21List;
    }

    public void setC21List(List<A21> c21List) {
        this.c21List = c21List;
    }

    public List<A22> getA22List() {
        return a22List;
    }

    public void setA22List(List<A22> a22List) {
        this.a22List = a22List;
    }

    public List<A22> getB22List() {
        return b22List;
    }

    public void setB22List(List<A22> b22List) {
        this.b22List = b22List;
    }

    public List<A22> getC22List() {
        return c22List;
    }

    public void setC22List(List<A22> c22List) {
        this.c22List = c22List;
    }

    public List<A23> getA23List() {
        return a23List;
    }

    public void setA23List(List<A23> a23List) {
        this.a23List = a23List;
    }

    public boolean isWriteSample() {
        return writeSample;
    }

    public void setWriteSample(boolean writeSample) {
        this.writeSample = writeSample;
    }

}
