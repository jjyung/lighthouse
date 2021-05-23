package tw.kgips.persistence.dao;

import org.springframework.stereotype.Repository;
import tw.kgips.persistence.entity.ListedSecurityEntity;

import java.util.List;

@Repository()
public class ListedSecurityDao extends AbstractHibernateDao {

    public ListedSecurityEntity getListedSecurity(String companyCode) {
        return getCurrentSession().createQuery("select ls " +
            " from ListedSecurityEntity as ls " +
            " where ls.companyCode = :companyCode", ListedSecurityEntity.class)
            .setParameter("companyCode", companyCode)
            .uniqueResult();
    }

    public List<ListedSecurityEntity> listListedSecuritiesByMarketCat(String marketCat) {
        return getCurrentSession().createQuery("select ls " +
            " from ListedSecurityEntity as ls " +
            " where :marketCat is null or ls.marketCat = :marketCat " +
            " order by ls.companyCode asc ", ListedSecurityEntity.class)
            .setParameter("marketCat", marketCat)
            .list();
    }

}
