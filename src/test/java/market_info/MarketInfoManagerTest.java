package market_info;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.kgips.config.AppConfig;
import tw.kgips.manager.MarketInfoManager;

@SpringBootTest(classes = {AppConfig.class})
@RunWith(SpringRunner.class)
public class MarketInfoManagerTest {

    @Autowired
    MarketInfoManager marketInfoManager;

    @Test
    public void testCrawlerAndUpdateMarketInfo() throws Exception {

        marketInfoManager.crawlAndUpdateMarketInfo();

    }

}
