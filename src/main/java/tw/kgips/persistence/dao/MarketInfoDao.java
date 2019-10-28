package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.MarketInfoEntity;

@Repository()
public class MarketInfoDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createMarketInfo(MarketInfoEntity entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	public MarketInfoEntity getHello(Long sn) {
		return sessionFactory.getCurrentSession().get(MarketInfoEntity.class, sn);
	}

}
