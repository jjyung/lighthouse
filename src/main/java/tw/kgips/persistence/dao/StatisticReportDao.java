package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.StatisticReportEntity;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public class StatisticReportDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createStatisticReport(StatisticReportEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public StatisticReportEntity getStatisticReport(String companyCode, LocalDate date) {
        return sessionFactory.getCurrentSession()
                .createQuery("select StatisticReportEntity " +
                        " from StatisticReportEntity " +
                        " where companyCode = :companyCode and date = :date", StatisticReportEntity.class)
                .setParameter("companyCode", companyCode)
                .setParameter("date", date)
                .uniqueResult();
    }

}
