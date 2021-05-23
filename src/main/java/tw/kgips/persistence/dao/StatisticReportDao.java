package tw.kgips.persistence.dao;

import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.StatisticReportEntity;
import tw.kgips.util.ConvertUtil;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public class StatisticReportDao extends AbstractHibernateDao {

    public void createStatisticReport(StatisticReportEntity entity) {
        getCurrentSession().save(entity);
    }

    public StatisticReportEntity getStatisticReport(String companyCode, LocalDate date) {
        return getCurrentSession()
            .createQuery("select StatisticReportEntity " +
                " from StatisticReportEntity " +
                " where companyCode = :companyCode and date = :date", StatisticReportEntity.class)
            .setParameter("companyCode", companyCode)
            .setParameter("date", date)
            .uniqueResult();
    }

    public boolean isStatisticReportExist(String companyCode, LocalDate date) {
        return ConvertUtil.toInt(getCurrentSession()
            .createQuery("select count(*) " +
                " from StatisticReportEntity " +
                " where companyCode = :companyCode" +
                " and date = :date")
            .setParameter("companyCode", companyCode)
            .setParameter("date", date)
            .uniqueResult()) > 0;
    }

}
