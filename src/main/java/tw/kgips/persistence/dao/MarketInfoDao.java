package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.MarketInfoEntity;

import java.sql.Date;

@Repository()
public class MarketInfoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(MarketInfoEntity entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public MarketInfoEntity get(Date date) {
        return (MarketInfoEntity) sessionFactory.getCurrentSession().createQuery("from MarketInfoEntity " +
                " where dateTime = :date")
                .setParameter("date", date)
                .uniqueResult();
    }
    // TODO UPDATE DELETE

}
