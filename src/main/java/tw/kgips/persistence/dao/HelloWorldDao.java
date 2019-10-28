package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.HelloWorldEntity;

@Repository()
public class HelloWorldDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createHello(HelloWorldEntity helloEntity) {
		sessionFactory.getCurrentSession().save(helloEntity);
	}

	public HelloWorldEntity getHello(Long sn) {
		return sessionFactory.getCurrentSession().get(HelloWorldEntity.class, sn);
	}

}
