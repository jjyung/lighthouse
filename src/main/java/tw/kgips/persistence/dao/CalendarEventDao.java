package tw.kgips.persistence.dao;

import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.HelloWorldEntity;

@Repository()
public class CalendarEventDao extends AbstractHibernateDao {

    public void createHello(HelloWorldEntity helloEntity) {
        getCurrentSession().save(helloEntity);
    }

    public HelloWorldEntity getHello(Long sn) {
        return getCurrentSession().get(HelloWorldEntity.class, sn);
    }

}
