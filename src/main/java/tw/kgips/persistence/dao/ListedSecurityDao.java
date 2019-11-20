package tw.kgips.persistence.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.ListedSecurityEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository()
@Transactional
public class ListedSecurityDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ListedSecurityEntity getListedSecurity(String companyCode) {
		return sessionFactory.getCurrentSession().createQuery("from ListedSecurityEntity " +
				" where companyCode = :companyCode", ListedSecurityEntity.class)
				.setParameter("companyCode", companyCode)
				.uniqueResult();
	}

	public List<ListedSecurityEntity> listListedSecuritiesByMarketCat(String marketCat) {
		return sessionFactory.getCurrentSession().createQuery("from ListedSecurityEntity " +
				" where marketCat is null or marketCat = :marketCat " +
				" order by companyCode asc ", ListedSecurityEntity.class)
				.setParameter("marketCat", marketCat)
				.list();
	}

}
