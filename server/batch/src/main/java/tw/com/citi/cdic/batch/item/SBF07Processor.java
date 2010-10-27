package tw.com.citi.cdic.batch.item;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.model.A21;
import tw.com.citi.cdic.batch.model.A22;
import tw.com.citi.cdic.batch.model.A23;
import tw.com.citi.cdic.batch.model.A26;
import tw.com.citi.cdic.batch.model.CDICF07H;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public class SBF07Processor implements ItemProcessor<CDICF07H, A26> {

    private A21Dao a21Dao;

    private A22Dao a22Dao;

    private A23Dao a23Dao;

    private int type;

    private A26 createA26(CDICF07H item) throws Exception {
        A26 a26 = new A26();
        if (item.getDescription().startsWith("DRC#")) {
            A23 a23 = a23Dao.findBySrNo(item.getDescription().substring(4, 14));
            if (a23 != null) {
                a26.setSrNo(a23.getSrNo());
                a26.setCustomerId(a23.getCustomerId());
                a26.setBranchNo(a23.getBranchNo());
            } else {
                a26.setSrNo("");
                a26.setCustomerId("");
                a26.setBranchNo("0000");
            }
        } else {
            if (StringUtils.isNumeric(item.getDescription().substring(0, 10))) {
                String srNo = item.getDescription().substring(0, 10);
                A21 a21 = a21Dao.findBySrNo(srNo, "A21");
                if (a21 != null) {
                    a26.setSrNo(a21.getSrNo());
                    a26.setCustomerId(a21.getCustomerId());
                    a26.setBranchNo(a21.getBranchNo());
                } else {
                    A22 a22 = a22Dao.findBySrNo(srNo, "A22");
                    if (a22 != null) {
                        a26.setSrNo(a22.getSrNo());
                        a26.setCustomerId(a22.getCustomerId());
                        a26.setBranchNo(a22.getBranchNo());
                    } else {
                        A23 a23 = a23Dao.findBySrNo(srNo);
                        if (a23 != null) {
                            a26.setSrNo(a23.getSrNo());
                            a26.setCustomerId(a23.getCustomerId());
                            a26.setBranchNo(a23.getBranchNo());
                        } else {
                            a26.setSrNo("");
                            a26.setCustomerId("");
                            a26.setBranchNo("0000");
                        }
                    }
                }
            } else {
                a26.setSrNo("");
                a26.setCustomerId("");
                a26.setBranchNo("0000");
            }
        }
        return a26;
    }

    private A26 createB26(CDICF07H item) throws Exception {
        A26 b26 = new A26();
        if (StringUtils.isNumeric(item.getDescription().substring(0, 10))) {
            String srNo = item.getDescription().substring(0, 10);
            A21 b21 = a21Dao.findBySrNo(srNo, "B21");
            if (b21 != null) {
                b26.setSrNo(b21.getSrNo());
                b26.setCustomerId(b21.getCustomerId());
                b26.setBranchNo(b21.getBranchNo());
            } else {
                A22 b22 = a22Dao.findBySrNo(srNo, "B22");
                if (b22 != null) {
                    b26.setSrNo(b22.getSrNo());
                    b26.setCustomerId(b22.getCustomerId());
                    b26.setBranchNo(b22.getBranchNo());
                } else {
                    b26.setSrNo("");
                    b26.setCustomerId("");
                    b26.setBranchNo("0000");
                }
            }
        } else {
            b26.setSrNo("");
            b26.setCustomerId("");
            b26.setBranchNo("0000");
        }
        return b26;
    }

    private A26 createC26(CDICF07H item) throws Exception {
        A26 c26 = new A26();
        if (StringUtils.isNumeric(item.getDescription().substring(0, 10))) {
            String srNo = item.getDescription().substring(0, 10);
            A21 c21 = a21Dao.findBySrNo(srNo, "C21");
            if (c21 != null) {
                c26.setSrNo(c21.getSrNo());
                c26.setCustomerId(c21.getCustomerId());
                c26.setBranchNo(c21.getBranchNo());
            } else {
                A22 c22 = a22Dao.findBySrNo(srNo, "C22");
                if (c22 != null) {
                    c26.setSrNo(c22.getSrNo());
                    c26.setCustomerId(c22.getCustomerId());
                    c26.setBranchNo(c22.getBranchNo());
                } else {
                    c26.setSrNo("");
                    c26.setCustomerId("");
                    c26.setBranchNo("0000");
                }
            }
        } else {
            c26.setSrNo("");
            c26.setCustomerId("");
            c26.setBranchNo("0000");
        }
        return c26;
    }

    @Override
    public A26 process(CDICF07H item) throws Exception {
        if ("8600".equals(item.getCompany())) {
            String numCode = item.getGl().substring(item.getGl().length() - 3, item.getGl().length());
            Configuration config = new HierarchicalINIConfiguration("currency_mappings.ini");
            String ccyCode = config.getString(numCode + ".code");
            if ("TWD".equals(ccyCode)) {
                // prepare data for a26
                if (type == 1) {
                    return createA26(item);
                } else {
                    return null;
                }
            } else {
                // prepare data for b26
                if (type == 2) {
                    return createB26(item);
                } else {
                    return null;
                }
            }
        } else {
            // prepare data for c26
            if (type == 3) {
                return createC26(item);
            } else {
                return null;
            }
        }
    }

    public void setA21Dao(A21Dao a21Dao) {
        this.a21Dao = a21Dao;
    }

    public void setA22Dao(A22Dao a22Dao) {
        this.a22Dao = a22Dao;
    }

    public void setA23Dao(A23Dao a23Dao) {
        this.a23Dao = a23Dao;
    }

    public void setType(int type) {
        this.type = type;
    }

}
