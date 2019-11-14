package tw.kgips.dto.exchange_day_report;

import tw.kgips.persistence.entity.ExchangeDayReportEntity;

import java.time.LocalDate;

public class ExchangeDayReportCreateDTO {

    private String companyCode; // 股票代碼

    private LocalDate date; // 日期

    private Long tradedSharesNumber; // 成交股數

    private Long txAmount; // 成交金額

    private Double openingPrice; // 開盤價

    private Double highestPrice; // 最高價

    private Double lowestPrice; // 最低價

    private Double closingPrice; // 收盤價

    private Double changeSpread; // 漲跌價差

    private Long txNumber; // 成交筆數

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getTradedSharesNumber() {
        return tradedSharesNumber;
    }

    public void setTradedSharesNumber(Long tradedSharesNumber) {
        this.tradedSharesNumber = tradedSharesNumber;
    }

    public Long getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(Long txAmount) {
        this.txAmount = txAmount;
    }

    public Double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(Double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public Double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Double highestPrice) {
        this.highestPrice = highestPrice;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Double getChangeSpread() {
        return changeSpread;
    }

    public void setChangeSpread(Double changeSpread) {
        this.changeSpread = changeSpread;
    }

    public Long getTxNumber() {
        return txNumber;
    }

    public void setTxNumber(Long txNumber) {
        this.txNumber = txNumber;
    }

    public ExchangeDayReportEntity toEntity() {
        ExchangeDayReportEntity entity = new ExchangeDayReportEntity();

        entity.setCompanyCode(this.getCompanyCode());
        entity.setDate(this.getDate());
        entity.setTradedSharesNumber(this.getTradedSharesNumber());
        entity.setTxAmount(this.getTxAmount());
        entity.setOpeningPrice(this.getOpeningPrice());
        entity.setHighestPrice(this.getHighestPrice());
        entity.setLowestPrice(this.getLowestPrice());
        entity.setClosingPrice(this.getClosingPrice());
        entity.setChangeSpread(this.getChangeSpread());
        entity.setTxNumber(this.getTxNumber());

        return entity;
    }

}
