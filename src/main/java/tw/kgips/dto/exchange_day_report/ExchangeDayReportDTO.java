package tw.kgips.dto.exchange_day_report;

import tw.kgips.persistence.entity.ExchangeDayReportEntity;

import java.time.LocalDate;

public class ExchangeDayReportDTO {

    private Long sn;

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

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

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

    public static ExchangeDayReportDTO fromEntity(ExchangeDayReportEntity entity) {
        ExchangeDayReportDTO dto = new ExchangeDayReportDTO();

        dto.setSn(entity.getSn());
        dto.setCompanyCode(entity.getCompanyCode());
        dto.setDate(entity.getDate());
        dto.setTradedSharesNumber(entity.getTradedSharesNumber());
        dto.setTxAmount(entity.getTxAmount());
        dto.setOpeningPrice(entity.getOpeningPrice());
        dto.setHighestPrice(entity.getHighestPrice());
        dto.setLowestPrice(entity.getLowestPrice());
        dto.setClosingPrice(entity.getClosingPrice());
        dto.setChangeSpread(entity.getChangeSpread());
        dto.setTxNumber(entity.getTxNumber());

        return dto;
    }

}
