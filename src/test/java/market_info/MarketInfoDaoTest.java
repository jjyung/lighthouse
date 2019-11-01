package market_info;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tw.kgips.persistence.dao.MarketInfoDao;
import tw.kgips.persistence.entity.MarketInfoEntity;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/spring-core-config.xml"})
public class MarketInfoDaoTest {

    @Autowired
    MarketInfoDao marketInfoDao;

    @Test
    public void testCreate() {
        MarketInfoEntity entity = new MarketInfoEntity();
        entity.setDate(OffsetDateTime.now().toLocalDate());
        marketInfoDao.create(entity);
    }

    @Test
    public void testRead() {

        LocalDate date = OffsetDateTime.now().toLocalDate();

        MarketInfoEntity entity = marketInfoDao.getMarketInfoByDate(date);

        if (entity == null) {
            testCreate();
            entity = marketInfoDao.getMarketInfoByDate(date);
        }

        Assert.assertNotNull(entity);

    }

    @Test
    public void testUpdate() {
        LocalDate date = OffsetDateTime.now().toLocalDate();

        MarketInfoEntity entity = marketInfoDao.getMarketInfoByDate(date);

        if (entity == null) {
            testCreate();
            entity = marketInfoDao.getMarketInfoByDate(date);
        }

        Assert.assertNotNull(entity);

        Double assertPC = Math.random();

        if (entity.getLargeTradersPutCallRatio() != null) {
            while (entity.getLargeTradersPutCallRatio().equals(assertPC)) {
                assertPC = Math.random();
            }
        }

        entity.setLargeTradersPutCallRatio(assertPC);

        marketInfoDao.updateMarketInfo(entity);

        Assert.assertEquals(assertPC, marketInfoDao.getMarketInfoByDate(entity.getDate()).getLargeTradersPutCallRatio());

    }

    @Test
    public void testDelete() {

        LocalDate now = OffsetDateTime.now().toLocalDate();

        MarketInfoEntity entity = marketInfoDao.getMarketInfoByDate(now);

        if (entity == null) {
            entity = new MarketInfoEntity();
            entity.setDate(now);
            marketInfoDao.create(entity);
            entity = marketInfoDao.getMarketInfoByDate(now);
        }

        marketInfoDao.deleteMarketInfoByDate(entity.getDate());

    }

}

