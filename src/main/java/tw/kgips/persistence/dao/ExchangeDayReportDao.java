package tw.kgips.persistence.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import tw.kgips.dto.DateRangeDTO;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;
import tw.kgips.util.ConvertUtil;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ExchangeDayReportDao extends AbstractHibernateDao {

    public void createExchangeDayReport(ExchangeDayReportEntity entity) {
        getCurrentSession().save(entity);
    }

    public boolean isExchangeDayReportExist(String companyCode, LocalDate date) {
        return ConvertUtil.toInt(getCurrentSession()
            .createQuery("select count(*) " +
                " from ExchangeDayReportEntity " +
                " where companyCode = :companyCode and date = :date")
            .setParameter("companyCode", companyCode)
            .setParameter("date", date)
            .uniqueResult()) > 0;
    }

    public ExchangeDayReportEntity getExchangeDayReport(String companyCode, LocalDate date) {
        return (ExchangeDayReportEntity) getCurrentSession()
            .createQuery("from ExchangeDayReportEntity " +
                " where companyCode = :companyCode and date = :date")
            .setParameter("companyCode", companyCode)
            .setParameter("date", date)
            .uniqueResult();
    }

    public List<ExchangeDayReportEntity> getLastExchangeDayReport(String companyCode, int recentDays) {
        return getCurrentSession()
            .createQuery("select exdr " +
                " from ExchangeDayReportEntity as exdr " +
                " where exdr.companyCode = :companyCode " +
                " order by exdr.date desc", ExchangeDayReportEntity.class)
            .setParameter("companyCode", companyCode)
            .setMaxResults(recentDays)
            .list();
    }

    public Object[] getMaxPriceMinPriceAvgPriceAvgTradedSharesNumOf(String companyCode, LocalDate date, int days) {

        return (Object[]) getCurrentSession()
            .createNativeQuery(
                "select max(highest_price), min(lowest_price), avg(closing_price), round(avg(traded_shares_number)) " +
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
            .uniqueResult();
    }

    public Double getMaxPriceOf(String companyCode, LocalDate date, int days) {
        return ConvertUtil.toDouble(getCurrentSession()
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

    public Double getMinPriceOf(String companyCode, LocalDate date, int days) {
        return ConvertUtil.toDouble(getCurrentSession()
            .createNativeQuery("select min(lowest_price) " +
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
        return ConvertUtil.toDouble(getCurrentSession()
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

    public Long getAvgTradedSharesNumOf(String companyCode, LocalDate date, int days) {
        return ConvertUtil.toLong(getCurrentSession()
            .createNativeQuery("select round(avg(traded_shares_number)) " +
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

    public List<ExchangeDayReportEntity> listExchangeDayReportByCompanyCodeAndDateRange(
        String companyCode,
        DateRangeDTO dateRangeDTO) {

        String queryString = "from ExchangeDayReportEntity " +
            " where companyCode = :companyCode" +
            (dateRangeDTO.getStartDate() != null ?
                " and date >" + (dateRangeDTO.isIncludeStart() ? "=" : "") + ":startDate" : "") +
            (dateRangeDTO.getEndDate() != null ? " and date <" + (dateRangeDTO.isIncludeEnd() ? "=" : "") + ":endDate" :
                "");

        Query<ExchangeDayReportEntity> query = getCurrentSession()
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
