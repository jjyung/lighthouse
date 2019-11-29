package tw.kgips.dto;

import tw.kgips.persistence.entity.StatisticReportEntity;

import java.time.LocalDate;

// TODO UPDATE
public class StatisticReportDTO {

    private String companyCode;

    private LocalDate date;

    private Double avgPrice5;

    private Double avgPrice10;

    private Double avgPrice20;

    private Double avgPrice60;

    private Double avgPrice120;

    private Double avgPrice200;

    private Double avgPrice240;

    private Long avgTxNum5;

    private Long avgTxNum10;

    private Long avgTxNum20;

    private Long avgTxNum60;

    private Long avgTxNum120;

    private Long avgTxNum200;

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

    public static StatisticReportDTO fromEntity(StatisticReportEntity entity) {

        if (entity == null) {
            return null;
        }

        StatisticReportDTO dto = new StatisticReportDTO();
        dto.setCompanyCode(entity.getCompanyCode());
        dto.setDate(entity.getDate());
        dto.setAvgPrice5(entity.getAvgPrice5());
        dto.setAvgPrice10(entity.getAvgPrice10());
        dto.setAvgPrice20(entity.getAvgPrice20());
        dto.setAvgPrice60(entity.getAvgPrice60());
        dto.setAvgPrice120(entity.getAvgPrice120());
        dto.setAvgPrice200(entity.getAvgPrice200());
        dto.setAvgPrice240(entity.getAvgPrice240());

        return dto;
    }
}
