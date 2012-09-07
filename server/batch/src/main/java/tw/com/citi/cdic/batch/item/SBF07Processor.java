package tw.com.citi.cdic.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import tw.com.citi.cdic.batch.dao.A11ADao;
import tw.com.citi.cdic.batch.dao.A21Dao;
import tw.com.citi.cdic.batch.dao.A22Dao;
import tw.com.citi.cdic.batch.dao.A23Dao;
import tw.com.citi.cdic.batch.model.A11;
import tw.com.citi.cdic.batch.model.A21;
import tw.com.citi.cdic.batch.model.A22;
import tw.com.citi.cdic.batch.model.A23;
import tw.com.citi.cdic.batch.model.A26;
import tw.com.citi.cdic.batch.model.A26.Type;
import tw.com.citi.cdic.batch.model.CDICF07H;

/**
 * @author Chih-Liang Chang
 * @since 2010/10/7
 */
public class SBF07Processor implements ItemProcessor<CDICF07H, List<A26>> {

    protected static final Logger logger = LoggerFactory.getLogger(SBF07Processor.class);

    private A21Dao a21Dao;

    private A22Dao a22Dao;

    private A23Dao a23Dao;

    private A11ADao a11ADao;

    private A26 prepareA26Instance(CDICF07H item) throws ConfigurationException {
        A26 a26 = new A26();
        a26.setUnit("021");
        a26.setApNo(item.getGl());
        a26.setPaySav(item.getAmount());
        a26.setIntPayable(item.getEffectDate());
        a26.setIntPayMemo(item.getDescription());
        String numCode = item.getGl().substring(item.getGl().length() - 3, item.getGl().length());
        Configuration config = new HierarchicalINIConfiguration("currency_mappings.ini");
        String ccyCode = config.getString(numCode + ".code");
        a26.setCurrencyCode(ccyCode);
        a26.setCompany(item.getCompany());
        a26.setRc(item.getRc());
        a26.setRefNo(item.getRefNo());
        return a26;
    }

    private List<A26> createA26(CDICF07H item, A26 a26) {
        List<A26> a26s = new ArrayList<A26>();
        boolean isDRCOrCLS = false;
        logger.debug(item.getDescription());
        if (item.getDescription().startsWith("DRC#") || item.getDescription().startsWith("CLS#")) {
            isDRCOrCLS = true;
        } else if (item.getDescription().length() >= 10
                && StringUtils.isNumeric(item.getDescription().substring(0, 10))) {
            String srNo = item.getDescription().substring(0, 10);

            A21 a21 = a21Dao.findBySrNo(srNo, "A21");
            if (a21 != null) {
                a26.setSrNo(a21.getSrNo());
                a26.setCustomerId(a21.getCustomerId());
                a26.setBranchNo(a21.getBranchNo());
                a26s.add(a26);
                return a26s;
            }

            A21 b21 = a21Dao.findBySrNo(srNo, "B21");
            if (b21 != null) {
                a26.setSrNo(b21.getSrNo());
                a26.setCustomerId(b21.getCustomerId());
                a26.setBranchNo(b21.getBranchNo());
                a26s.add(a26);
                return a26s;
            }

            A21 c21 = a21Dao.findBySrNo(srNo, "C21");
            if (c21 != null) {
                a26.setSrNo(c21.getSrNo());
                a26.setCustomerId(c21.getCustomerId());
                a26.setBranchNo(c21.getBranchNo());
                a26s.add(a26);
                return a26s;
            }

            A22 a22 = a22Dao.findBySrNo(srNo, "A22");
            if (a22 != null) {
                a26.setSrNo(a22.getSrNo());
                a26.setCustomerId(a22.getCustomerId());
                a26.setBranchNo(a22.getBranchNo());
                a26s.add(a26);
                return a26s;
            }

            A22 b22 = a22Dao.findBySrNo(srNo, "B22");
            if (b22 != null) {
                a26.setSrNo(b22.getSrNo());
                a26.setCustomerId(b22.getCustomerId());
                a26.setBranchNo(b22.getBranchNo());
                a26s.add(a26);
                return a26s;
            }

            A22 c22 = a22Dao.findBySrNo(srNo, "C22");
            if (c22 != null) {
                a26.setSrNo(c22.getSrNo());
                a26.setCustomerId(c22.getCustomerId());
                a26.setBranchNo(c22.getBranchNo());
                a26s.add(a26);
                return a26s;
            }

            A23 a23 = a23Dao.findBySrNo(srNo);
            if (a23 != null) {
                a26.setSrNo(a23.getSrNo());
                a26.setCustomerId(a23.getCustomerId());
                a26.setBranchNo(a23.getBranchNo());
                a26s.add(a26);
                return a26s;
            }
        }

        // 執行到此代表 A21、A22、A23 都 mapping 不到資料。
        // 如果是 DRC/CLS，用 4,14 去 mapping CDICF01 的 HEADID，取 GRB、AccountNo
        // 否則用 0,10 去 mapping CDICF01 的 AccountNo 取 GRB、AccountNo

        if (isDRCOrCLS) {
            List<A11> a11as = a11ADao.findByHeadId(item.getDescription().substring(4, 14));
            for (A11 a11a : a11as) {
                try {
                    A26 a26n = (A26) BeanUtils.cloneBean(a26);
                    a26n.setSrNo(a11a.getSrNo());
                    a26n.setCustomerId(a11a.getId());
                    a26n.setBranchNo("0018");
                    a26s.add(a26n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return a26s;

        } else {
            if (item.getDescription().length() >= 10) {
                A11 a11a = a11ADao.findByAccountNo(item.getDescription().substring(0, 10));
                if (a11a != null) {
                    a26.setSrNo(a11a.getSrNo());
                    a26.setCustomerId(a11a.getId());
                    a26.setBranchNo("0018");
                    a26s.add(a26);
                    return a26s;
                }
            }
        }
        return a26s;
    }

    @Override
    public List<A26> process(CDICF07H item) throws Exception {
        if (item.getRefNo() == null || item.getRefNo().trim().length() == 0) {
            logger.info("Ignore CDICF07. [GL = {}], [Company code = {}], [Reference number = {}], [RC code = {}]",
                    new Object[] { item.getGl(), item.getCompany(), item.getRefNo(), item.getRc() });
            return null;
        }
        List<A26> a26s = new ArrayList<A26>();
        A26 a26 = prepareA26Instance(item);

        if ("8600".equals(item.getCompany())) {
            if (item.getGl().endsWith("000")) {
                // prepare data for a26
                a26.setType(Type.A);
                a26s.addAll(createA26(item, a26));
            } else {
                logger.info("Ignore CDICF07. [GL = {}], [Company code = {}], [Reference number = {}], [RC code = {}]",
                        new Object[] { item.getGl(), item.getCompany(), item.getRefNo(), item.getRc() });
                return null;
            }
        } else if (item.getCompany().startsWith("86")) {
            // prepare data for b26
            a26.setType(Type.B);
            a26s.addAll(createA26(item, a26));
        } else if ("6900".equals(item.getCompany())) {
            if (item.getGl().endsWith("000")) {
                // prepare data for c26
                a26.setType(Type.C);
                a26s.addAll(createA26(item, a26));
            } else {
                logger.info("Ignore CDICF07. [GL = {}], [Company code = {}], [Reference number = {}], [RC code = {}]",
                        new Object[] { item.getGl(), item.getCompany(), item.getRefNo(), item.getRc() });
                return null;
            }
        } else if (item.getCompany().startsWith("69")) {
            // prepare data for c26
            a26.setType(Type.C);
            a26s.addAll(createA26(item, a26));
        } else {
            logger.info("Ignore CDICF07. [GL = {}], [Company code = {}], [Reference number = {}], [RC code = {}]",
                    new Object[] { item.getGl(), item.getCompany(), item.getRefNo(), item.getRc() });
            return null;
        }

        return a26s;
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

    public void setA11ADao(A11ADao a11ADao) {
        this.a11ADao = a11ADao;
    }

    public A11ADao getA11ADao() {
        return a11ADao;
    }

}
