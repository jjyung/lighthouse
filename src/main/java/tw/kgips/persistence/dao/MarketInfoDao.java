package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.MarketInfoEntity;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository()
@Transactional
public class MarketInfoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createMarketInfo(MarketInfoEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public MarketInfoEntity getMarketInfoByDate(LocalDate date) {
        return (MarketInfoEntity) sessionFactory.getCurrentSession().createQuery("from MarketInfoEntity " +
                " where date = :date")
                .setParameter("date", date)
                .uniqueResult();
    }

    public void updateMarketInfo(MarketInfoEntity entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public void deleteMarkeyInfoBySn(long sn) {
        sessionFactory.getCurrentSession().createQuery("delete from MarketInfoEntity where sn = :sn")
                .setParameter("sn", sn)
                .executeUpdate();
    }

    public void deleteMarketInfoByDate(LocalDate date) {
        sessionFactory.getCurrentSession().createQuery("delete from MarketInfoEntity where date = :date")
                .setParameter("date", date)
                .executeUpdate();
    }

}
