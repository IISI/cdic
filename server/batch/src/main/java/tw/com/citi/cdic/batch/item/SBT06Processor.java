package tw.com.citi.cdic.batch.item;

import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.T01Dao;
import tw.com.citi.cdic.batch.model.T01;

/**
 * @author Lancelot
 * @since 2010/10/11
 */
public class SBT06Processor implements ItemProcessor<T01, T01> {

    private T01Dao t06Dao;

    @Override
    public T01 process(T01 item) throws Exception {
        T01 t06 = null;
        if (item != null) {
            t06 = new T01();
            t06.setCode(item.getCode());
            t06.setDescription(item.getDescription());
        }
        return t06;
    }

    public void setT06Dao(T01Dao t06Dao) {
        this.t06Dao = t06Dao;
    }

    public T01Dao getT06Dao() {
        return t06Dao;
    }
}
