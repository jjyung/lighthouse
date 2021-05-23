package tw.kgips.persistence.dao;

import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.MarketInfoEntity;

import java.time.LocalDate;

@Repository()
public class MarketInfoDao extends AbstractHibernateDao {

    public void createMarketInfo(MarketInfoEntity entity) {
        getCurrentSession().save(entity);
    }

    public MarketInfoEntity getMarketInfoByDate(LocalDate date) {
        return getCurrentSession().createQuery("select MarketInfoEntity " +
            " from MarketInfoEntity " +
            " where date = :date", MarketInfoEntity.class)
            .setParameter("date", date)
            .uniqueResult();
    }

    public void updateMarketInfo(MarketInfoEntity entity) {
        getCurrentSession().update(entity);
    }

    public void deleteMarketInfoBySn(long sn) {
        getCurrentSession().createQuery("delete from MarketInfoEntity where sn = :sn")
            .setParameter("sn", sn)
            .executeUpdate();
    }

    public void deleteMarketInfoByDate(LocalDate date) {
        getCurrentSession().createQuery("delete from MarketInfoEntity where date = :date")
            .setParameter("date", date)
            .executeUpdate();
    }

}
