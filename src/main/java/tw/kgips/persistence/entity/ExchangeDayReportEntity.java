package tw.kgips.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_day_report",
    uniqueConstraints = @UniqueConstraint(name = "exchange_day_report_uk", columnNames = { "company_code", "date" }))
public class ExchangeDayReportEntity {

    @Id
    @Column(name = "sn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;

    @Column(name = "company_code", length = 15)
    private String companyCode; // 股票代碼

    @Column(name = "date")
    private LocalDate date; // 日期

    @Column(name = "traded_shares_number")
    private Long tradedSharesNumber; // 成交股數

    @Column(name = "tx_amount")
    private Long txAmount; // 成交金額

    @Column(name = "opening_price")
    private Double openingPrice; // 開盤價

    @Column(name = "highest_price")
    private Double highestPrice; // 最高價

    @Column(name = "lowest_price")
    private Double lowestPrice; // 最低價

    @Column(name = "closing_price")
    private Double closingPrice; // 收盤價

    @Column(name = "change_spread")
    private Double changeSpread; // 漲跌價差

    @Column(name = "tx_number")
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

    public void setTradedSharesNumber(Long tradedSharesMumber) {
        this.tradedSharesNumber = tradedSharesMumber;
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
}
