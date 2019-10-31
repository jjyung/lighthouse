package tw.kgips.dto.hello_world;

import tw.kgips.persistence.entity.MarketInfoEntity;

import java.time.LocalDate;

public class MarketInfoDTO {

    private Long sn;

    // 日期
    private LocalDate dateTime;

    // 外資留倉口數
    private Long finiOpenInterestNetAmount;

    // 外資大台成本
    private Long finiTxCost;

    // 大額交易人選擇權 PC ratio
    private Double largeTradersPutCallRatio;

    // 外資現貨買超金額
    private Long finiStockTradingValueNetAmount;

    // 匯率
    private Double dollarExchangeRate;

    // 淨值比
    private Double priceToBookValue;

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Long getFiniOpenInterestNetAmount() {
        return finiOpenInterestNetAmount;
    }

    public void setFiniOpenInterestNetAmount(Long finiOpenInterestNetAmount) {
        this.finiOpenInterestNetAmount = finiOpenInterestNetAmount;
    }

    public Long getFiniTxCost() {
        return finiTxCost;
    }

    public void setFiniTxCost(Long finiTxCost) {
        this.finiTxCost = finiTxCost;
    }

    public Double getLargeTradersPutCallRatio() {
        return largeTradersPutCallRatio;
    }

    public void setLargeTradersPutCallRatio(Double largeTradersPutCallRatio) {
        this.largeTradersPutCallRatio = largeTradersPutCallRatio;
    }

    public Long getFiniStockTradingValueNetAmount() {
        return finiStockTradingValueNetAmount;
    }

    public void setFiniStockTradingValueNetAmount(Long finiStockTradingValueNetAmount) {
        this.finiStockTradingValueNetAmount = finiStockTradingValueNetAmount;
    }

    public Double getDollarExchangeRate() {
        return dollarExchangeRate;
    }

    public void setDollarExchangeRate(Double dollarExchangeRate) {
        this.dollarExchangeRate = dollarExchangeRate;
    }

    public Double getPriceToBookValue() {
        return priceToBookValue;
    }

    public void setPriceToBookValue(Double priceToBookValue) {
        this.priceToBookValue = priceToBookValue;
    }

    public static MarketInfoDTO fromEntity(MarketInfoEntity entity) {

        if (entity == null) {
            return null;
        }

        MarketInfoDTO dto = new MarketInfoDTO();

        dto.setSn(entity.getSn());
        dto.setDateTime(entity.getDate());
        dto.setFiniOpenInterestNetAmount(entity.getFiniOpenInterestNetAmount());
        dto.setFiniTxCost(entity.getFiniTxCost());
        dto.setLargeTradersPutCallRatio(entity.getLargeTradersPutCallRatio());
        dto.setFiniStockTradingValueNetAmount(entity.getFiniStockTradingValueNetAmount());
        dto.setDollarExchangeRate(entity.getDollarExchangeRate());
        dto.setPriceToBookValue(entity.getPriceToBookValue());

        return dto;
    }
}
