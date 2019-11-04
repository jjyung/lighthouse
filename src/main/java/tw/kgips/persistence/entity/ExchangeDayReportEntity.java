package tw.kgips.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_day_report")
public class ExchangeDayReportEntity {

    @Id
    @Column(name = "company_code")
    private String companyCode; // 股票代碼

    @Column(name = "date")
    private LocalDateTime date; // 日期

    @Column(name = "traded_shares_number")
    private Long tradedSharesMumber; // 成交股數

    @Column(name = "tx_amount")
    private Long txAmount; // 成交金額

    @Column(name = "opening_price")
    private Double openingPrice; // 開盤價

    @Column(name = "highest_price")
    private Double highestPrice; // 最高價

    @Column(name = "lowest_price")
    private Double lowestPrice; // 最低價

    @Column(name = "closing_price")
    private Double closeingPrice; // 收盤價

    @Column(name = "change_spread")
    private Double changeSpread; // 漲跌價差

    @Column(name = "tx_number")
    private Long txNumber; // 成交筆數

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getTradedSharesMumber() {
        return tradedSharesMumber;
    }

    public void setTradedSharesMumber(Long tradedSharesMumber) {
        this.tradedSharesMumber = tradedSharesMumber;
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

    public Double getCloseingPrice() {
        return closeingPrice;
    }

    public void setCloseingPrice(Double closeingPrice) {
        this.closeingPrice = closeingPrice;
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
}
