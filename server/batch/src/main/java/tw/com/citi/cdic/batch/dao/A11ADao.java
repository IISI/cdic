package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.A11;

public interface A11ADao {
    List<A11> findByHeadId(String id);

    A11 findByAccountNo(String srNo);
}
