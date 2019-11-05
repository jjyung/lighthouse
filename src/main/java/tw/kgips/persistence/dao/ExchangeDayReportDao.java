package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.dto.DateRangeDTO;
import tw.kgips.persistence.entity.ExchangeDayReportEntity;

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

    public ExchangeDayReportEntity getExchangeDayReport(String companyCode, LocalDate date) {
        return (ExchangeDayReportEntity) sessionFactory.getCurrentSession()
                .createQuery("from ExchangeDayReportEntity " +
                        " where companyCode = :companyCode and date = :date")
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .uniqueResult();
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
