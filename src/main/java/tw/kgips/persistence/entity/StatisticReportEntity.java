package tw.kgips.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "statistic_report", uniqueConstraints = {
        @UniqueConstraint(name = "statistic_report_uk", columnNames = {"company_code", "date"})
})
public class StatisticReportEntity {

    @Id
    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "date")
    private LocalDate date;

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
