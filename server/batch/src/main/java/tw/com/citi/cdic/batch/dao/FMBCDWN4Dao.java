package tw.com.citi.cdic.batch.dao;

import tw.com.citi.cdic.batch.model.FMBCDWN4;

public interface FMBCDWN4Dao {
    FMBCDWN4 findByAcctAndIBCode(String acct, String IBCode);
}
