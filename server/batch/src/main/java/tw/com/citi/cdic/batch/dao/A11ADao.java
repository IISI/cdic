package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.A11;

public interface A11ADao {
    A11 findById(String id);

    A11 findByAccountNo(String accountNo);
}
