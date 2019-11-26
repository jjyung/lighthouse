package tw.kgips.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "statistic_report", uniqueConstraints = {
		@UniqueConstraint(name = "statistic_report_uk", columnNames = {"company_code", "date"})
})
public class StatisticReportEntity {

	@Id
	@Column(name = "sn")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sn;

	@Column(name = "company_code")
	private String companyCode;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "max_price_5")
	private Double maxPrice5;

	@Column(name = "max_price_10")
	private Double maxPrice10;

	@Column(name = "max_price_20")
	private Double maxPrice20;

	@Column(name = "max_price_60")
	private Double maxPrice60;

	@Column(name = "max_price_120")
	private Double maxPrice120;

	@Column(name = "max_price_200")
	private Double maxPrice200;

	@Column(name = "max_price_240")
	private Double maxPrice240;

	@Column(name = "min_price_5")
	private Double minPrice5;

	@Column(name = "min_price_10")
	private Double minPrice10;

	@Column(name = "min_price_20")
	private Double minPrice20;

	@Column(name = "min_price_60")
	private Double minPrice60;

	@Column(name = "min_price_120")
	private Double minPrice120;

	@Column(name = "min_price_200")
	private Double minPrice200;

	@Column(name = "min_price_240")
	private Double minPrice240;

	@Column(name = "avg_price_5")
	private Double avgPrice5;

	@Column(name = "avg_price_10")
	private Double avgPrice10;

	@Column(name = "avg_price_20")
	private Double avgPrice20;

	@Column(name = "avg_price_60")
	private Double avgPrice60;

	@Column(name = "avg_price_120")
	private Double avgPrice120;

	@Column(name = "avg_price_200")
	private Double avgPrice200;

	@Column(name = "avg_price_240")
	private Double avgPrice240;

	@Column(name = "avg_tx_num_5")
	private Long avgTxNum5;

	@Column(name = "avg_tx_num_10")
	private Long avgTxNum10;

	@Column(name = "avg_tx_num_20")
	private Long avgTxNum20;

	@Column(name = "avg_tx_num_60")
	private Long avgTxNum60;

	@Column(name = "avg_tx_num_120")
	private Long avgTxNum120;

	@Column(name = "avg_tx_num_200")
	private Long avgTxNum200;

	@Column(name = "avg_tx_num_240")
	private Long avgTxNum240;

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

	public Double getMaxPrice5() {
		return maxPrice5;
	}

	public void setMaxPrice5(Double maxPrice5) {
		this.maxPrice5 = maxPrice5;
	}

	public Double getMaxPrice10() {
		return maxPrice10;
	}

	public void setMaxPrice10(Double maxPrice10) {
		this.maxPrice10 = maxPrice10;
	}

	public Double getMaxPrice20() {
		return maxPrice20;
	}

	public void setMaxPrice20(Double maxPrice20) {
		this.maxPrice20 = maxPrice20;
	}

	public Double getMaxPrice60() {
		return maxPrice60;
	}

	public void setMaxPrice60(Double maxPrice60) {
		this.maxPrice60 = maxPrice60;
	}

	public Double getMaxPrice120() {
		return maxPrice120;
	}

	public void setMaxPrice120(Double maxPrice120) {
		this.maxPrice120 = maxPrice120;
	}

	public Double getMaxPrice200() {
		return maxPrice200;
	}

	public void setMaxPrice200(Double maxPrice200) {
		this.maxPrice200 = maxPrice200;
	}

	public Double getMaxPrice240() {
		return maxPrice240;
	}

	public void setMaxPrice240(Double maxPrice240) {
		this.maxPrice240 = maxPrice240;
	}

	public Double getMinPrice5() {
		return minPrice5;
	}

	public void setMinPrice5(Double minPrice5) {
		this.minPrice5 = minPrice5;
	}

	public Double getMinPrice10() {
		return minPrice10;
	}

	public void setMinPrice10(Double minPrice10) {
		this.minPrice10 = minPrice10;
	}

	public Double getMinPrice20() {
		return minPrice20;
	}

	public void setMinPrice20(Double minPrice20) {
		this.minPrice20 = minPrice20;
	}

	public Double getMinPrice60() {
		return minPrice60;
	}

	public void setMinPrice60(Double minPrice60) {
		this.minPrice60 = minPrice60;
	}

	public Double getMinPrice120() {
		return minPrice120;
	}

	public void setMinPrice120(Double minPrice120) {
		this.minPrice120 = minPrice120;
	}

	public Double getMinPrice200() {
		return minPrice200;
	}

	public void setMinPrice200(Double minPrice200) {
		this.minPrice200 = minPrice200;
	}

	public Double getMinPrice240() {
		return minPrice240;
	}

	public void setMinPrice240(Double minPrice240) {
		this.minPrice240 = minPrice240;
	}

	public Double getAvgPrice5() {
		return avgPrice5;
	}

	public void setAvgPrice5(Double avgPrice5) {
		this.avgPrice5 = avgPrice5;
	}

	public Double getAvgPrice10() {
		return avgPrice10;
	}

	public void setAvgPrice10(Double avgPrice10) {
		this.avgPrice10 = avgPrice10;
	}

	public Double getAvgPrice20() {
		return avgPrice20;
	}

	public void setAvgPrice20(Double avgPrice20) {
		this.avgPrice20 = avgPrice20;
	}

	public Double getAvgPrice60() {
		return avgPrice60;
	}

	public void setAvgPrice60(Double avgPrice60) {
		this.avgPrice60 = avgPrice60;
	}

	public Double getAvgPrice120() {
		return avgPrice120;
	}

	public void setAvgPrice120(Double avgPrice120) {
		this.avgPrice120 = avgPrice120;
	}

	public Double getAvgPrice200() {
		return avgPrice200;
	}

	public void setAvgPrice200(Double avgPrice200) {
		this.avgPrice200 = avgPrice200;
	}

	public Double getAvgPrice240() {
		return avgPrice240;
	}

	public void setAvgPrice240(Double avgPrice240) {
		this.avgPrice240 = avgPrice240;
	}

	public Long getAvgTxNum5() {
		return avgTxNum5;
	}

	public void setAvgTxNum5(Long avgTxNum5) {
		this.avgTxNum5 = avgTxNum5;
	}

	public Long getAvgTxNum10() {
		return avgTxNum10;
	}

	public void setAvgTxNum10(Long avgTxNum10) {
		this.avgTxNum10 = avgTxNum10;
	}

	public Long getAvgTxNum20() {
		return avgTxNum20;
	}

	public void setAvgTxNum20(Long avgTxNum20) {
		this.avgTxNum20 = avgTxNum20;
	}

	public Long getAvgTxNum60() {
		return avgTxNum60;
	}

	public void setAvgTxNum60(Long avgTxNum60) {
		this.avgTxNum60 = avgTxNum60;
	}

	public Long getAvgTxNum120() {
		return avgTxNum120;
	}

	public void setAvgTxNum120(Long avgTxNum120) {
		this.avgTxNum120 = avgTxNum120;
	}

	public Long getAvgTxNum200() {
		return avgTxNum200;
	}

	public void setAvgTxNum200(Long avgTxNum200) {
		this.avgTxNum200 = avgTxNum200;
	}

	public Long getAvgTxNum240() {
		return avgTxNum240;
	}

	public void setAvgTxNum240(Long avgTxNum240) {
		this.avgTxNum240 = avgTxNum240;
	}
}
