package tw.kgips.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "market_info", uniqueConstraints = {
		@UniqueConstraint(name = "market_unique_key", columnNames = {"date_time"})
})
public class MarketInfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sn")
	private Long sn;

	// 日期
	@Column(name = "date_time")
	private LocalDate dateTime;

	// 外資留倉口數
	@Column(name = "fini_open_interest_net_amount")
	private Long finiOpenInterestNetAmount;

	// 外資大台成本
	@Column(name = "fini_tx_cost")
	private Long finiTxCost;

	// 大額交易人選擇權 PC ratio
	@Column(name = "large_traders_put_call_ratio")
	private Double largeTradersPutCallRatio;

	// 外資現貨買超金額
	@Column(name = "fini_stock_trading_value_net_amount")
	private Long finiStockTradingValueNetAmount;

	// 匯率
	@Column(name = "dollar_exchange_rate")
	private Double dollarExchangeRate;

	// 淨值比
	@Column(name = "price_to_book_value")
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
}
