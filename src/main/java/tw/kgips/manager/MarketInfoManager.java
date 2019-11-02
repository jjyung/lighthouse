package tw.kgips.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.kgips.dto.market_info.MarketInfoDTO;
import tw.kgips.persistence.dao.MarketInfoDao;
import tw.kgips.persistence.entity.MarketInfoEntity;
import tw.kgips.sandbox.FutureExchange;
import tw.kgips.sandbox.TWSE;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MarketInfoManager {

    private MarketInfoDao marketInfoDao;

    @Autowired
    public void setMarketInfoDao(MarketInfoDao marketInfoDao) {
        this.marketInfoDao = marketInfoDao;
    }

    public MarketInfoDTO getMarketInfoByDate(LocalDate date) {
        return MarketInfoDTO.fromEntity(this.marketInfoDao.getMarketInfoByDate(date));
    }

    @Transactional
    public void crawlAndUpdateMarketInfo() throws Exception {

        OffsetDateTime now = OffsetDateTime.now();

        MarketInfoEntity marketInfoEntity = marketInfoDao.getMarketInfoByDate(now.toLocalDate());

        if (marketInfoEntity == null) {
            marketInfoEntity = new MarketInfoEntity();
            marketInfoEntity.setDate(now.toLocalDate());
            marketInfoDao.create(marketInfoEntity);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        // 小數點第二位
        DecimalFormat df = new DecimalFormat("##.00");

        Long stockNetBuy = TWSE.getFININetBuyAmount();

        Long finiFutureNetAmount = FutureExchange.getFININetAmount();
        Double putCallRatio = FutureExchange.getPutCallRatio();

        marketInfoEntity.setFiniStockTradingValueNetAmount(stockNetBuy);
        marketInfoEntity.setFiniOpenInterestNetAmount(finiFutureNetAmount);
        marketInfoEntity.setLargeTradersPutCallRatio(putCallRatio);

        marketInfoDao.updateMarketInfo(marketInfoEntity);

        // TODO extract to service/controller/view
        // message
        String sellBuy = stockNetBuy > 0 ? "買" : "賣";
        String FININetBuyAmount = df.format(Math.abs(stockNetBuy) / 100000000.0);
        System.out.println(String.format("%s 外資大台淨多單 %s 口，p/c %s，外資現貨%s超 %s 億。",
                    formatter.format(now), finiFutureNetAmount, df.format(putCallRatio), sellBuy, FININetBuyAmount));

    }

}
