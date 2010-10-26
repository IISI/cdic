package tw.com.citi.cdic.batch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lancelot
 * @since 2010/10/25
 */
public class SBT06Output {

    private List<T01> t06List = new ArrayList<T01>();

    public void setT06List(List<T01> t06List) {
        this.t06List = t06List;
    }

    public List<T01> getT06List() {
        return t06List;
    }
}
