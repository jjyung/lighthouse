package listed_security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.config.AppConfig;
import tw.kgips.dto.listed_security.MarketCat;
import tw.kgips.manager.ListedSecurityManager;

@SpringBootTest(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ListedSecurityManagerTest {

	@Autowired
	ListedSecurityManager listedSecurityManager;

	private static final String testCompanyCode = "1101";

	@Test
	public void testGetListedSecurityByCompanyCode() {
		Assert.assertNotNull(listedSecurityManager.getListedSecurityByCompanyCode(testCompanyCode));
	}

	@Test
	public void testListListedSecuritiesByMarketCat() {

		listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.SII.getValue());
		listedSecurityManager.listListedSecuritiesByMarketCat(MarketCat.OTC.getValue());
		listedSecurityManager.listListedSecuritiesByMarketCat(null);

	}

}
