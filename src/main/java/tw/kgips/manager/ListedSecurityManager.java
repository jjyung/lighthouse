package tw.kgips.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.kgips.dto.listed_security.ListedSecurityDTO;
import tw.kgips.persistence.dao.ListedSecurityDao;
import tw.kgips.persistence.entity.ListedSecurityEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListedSecurityManager {

	private final ListedSecurityDao listedSecurityDao;

	@Autowired
	public ListedSecurityManager(ListedSecurityDao listedSecurityDao) {
		this.listedSecurityDao = listedSecurityDao;
	}

	public ListedSecurityDTO getListedSecurityByCompanyCode(String companyCode) {
		ListedSecurityEntity listedSecurity = listedSecurityDao.getListedSecurity(companyCode);
		return ListedSecurityDTO.fromEntity(listedSecurity);
	}

	public List<ListedSecurityDTO> listListedSecuritiesByMarketCat(String marketCat) {
		List<ListedSecurityEntity> listedSecurityEntities = listedSecurityDao.listListedSecuritiesByMarketCat(marketCat);

		List<ListedSecurityDTO> dtos = new ArrayList<>();

		for (ListedSecurityEntity entity : listedSecurityEntities) {
			dtos.add(ListedSecurityDTO.fromEntity(entity));
		}

		return dtos;
	}

}
