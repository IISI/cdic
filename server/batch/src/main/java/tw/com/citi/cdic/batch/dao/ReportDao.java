package tw.com.citi.cdic.batch.dao;

import java.util.List;

import tw.com.citi.cdic.batch.model.Report;

/**
 * @author Chih-Liang Chang
 * @since 2010/11/24
 */
public interface ReportDao {

    List<Report> findAll();

}
