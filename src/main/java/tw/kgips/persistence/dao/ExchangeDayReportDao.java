package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.dto.DateRangeDTO;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;
import tw.kgips.util.ConvertUtil;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class ExchangeDayReportDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createExchangeDayReport(ExchangeDayReportEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public boolean isExchangeDayReportExist(String companyCode, LocalDate date) {
        return ConvertUtil.toInt(sessionFactory.getCurrentSession()
                .createQuery("select count(*) " +
                        " from ExchangeDayReportEntity " +
                        " where companyCode = :companyCode and date = :date")
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .uniqueResult()) > 0;
    }

    public ExchangeDayReportEntity getExchangeDayReport(String companyCode, LocalDate date) {
        return (ExchangeDayReportEntity) sessionFactory.getCurrentSession()
                .createQuery("from ExchangeDayReportEntity " +
                        " where companyCode = :companyCode and date = :date")
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .uniqueResult();
    }

    public ExchangeDayReportEntity getLastExchangeDayReport(String companyCode) {
        return (ExchangeDayReportEntity) sessionFactory.getCurrentSession()
                .createQuery("from ExchangeDayReportEntity " +
                        " where companyCode = :companyCode " +
                        " order by date desc")
                .setParameter("companyCode", companyCode)
                .setMaxResults(1)
                .uniqueResult();
    }

    public Double getMaxPriceOf(String companyCode, LocalDate date, int days) {
        return ConvertUtil.toDouble(sessionFactory.getCurrentSession()
                .createNativeQuery("select max(highest_price) " +
                        " from ( " +
                        " select * " +
                        " from exchange_day_report " +
                        " where company_code = :companyCode " +
                        " and date <= :date" +
                        " order by date desc " +
                        " limit :days " +
                        ") as recent_k")
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .setParameter("days", days)
                .uniqueResult());
    }

    public Double getAvgPriceOf(String companyCode, LocalDate date, int days) {
        return ConvertUtil.toDouble(sessionFactory.getCurrentSession()
                .createNativeQuery("select avg(closing_price) " +
                        " from ( " +
                        " select * " +
                        " from exchange_day_report " +
                        " where company_code = :companyCode " +
                        " and date <= :date" +
                        " order by date desc " +
                        " limit :days " +
                        ") as recent_k")
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .setParameter("days", days)
                .uniqueResult());
    }

    public Long getAvgTxNumOf(String companyCode, LocalDate date, int days) {
        return ConvertUtil.toLong(sessionFactory.getCurrentSession()
                .createNativeQuery("select round(avg(tx_number)) " +
                        " from ( " +
                        " select * " +
                        " from exchange_day_report " +
                        " where company_code = :companyCode " +
                        " and date <= :date" +
                        " order by date desc " +
                        " limit :days " +
                        ") as recent_k")
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .setParameter("days", days)
                .uniqueResult());
    }

    public List<ExchangeDayReportEntity> listExchangeDayReportByCompanyCodeAndDateRange(String companyCode, DateRangeDTO dateRangeDTO) {

        String queryString = "from ExchangeDayReportEntity " +
                " where companyCode = :companyCode" +
                (dateRangeDTO.getStartDate() != null ? " and date >" + (dateRangeDTO.isIncludeStart() ? "=" : "") + ":startDate" : "") +
                (dateRangeDTO.getEndDate() != null ? " and date <" + (dateRangeDTO.isIncludeEnd() ? "=" : "") + ":endDate" : "");

        Query<ExchangeDayReportEntity> query = sessionFactory.getCurrentSession()
                .createQuery(queryString, ExchangeDayReportEntity.class)
                .setParameter("companyCode", companyCode);

        if (dateRangeDTO.getStartDate() != null) {
            query.setParameter("startDate", dateRangeDTO.getStartDate());
        }

        if (dateRangeDTO.getEndDate() != null) {
            query.setParameter("endDate", dateRangeDTO.getEndDate());
        }

        return query.list();
    }

}
