package market_info;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.manager.MarketInfoManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class MarketInfoManagerTest {

    @Autowired
    MarketInfoManager marketInfoManager;

    @Test
    public void testCrawlerAndUpdateMarketInfo() throws Exception {

        marketInfoManager.crawlAndUpdateMarketInfo();

    }

}
