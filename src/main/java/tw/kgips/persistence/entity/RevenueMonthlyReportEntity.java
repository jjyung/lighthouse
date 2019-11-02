package tw.kgips.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "revenue_monthly_report", uniqueConstraints = {
        @UniqueConstraint(name = "monthly_report_uk", columnNames = {"company_code", "year", "month"})
})
public class RevenueMonthlyReportEntity {

    @Id
    @Column(name = "sn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;

    @Column(name = "company_code", length = 15)
    private String companyCode;

    @Column(name = "company_name", length = 15)
    private String companyName;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "monthly_revenue")
    private Long monthlyRevenue;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(Long monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }
}
