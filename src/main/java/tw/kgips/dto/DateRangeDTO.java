package tw.kgips.dto;

import java.time.LocalDate;

public class DateRangeDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean includeStart;
    private boolean includeEnd;

    public DateRangeDTO(LocalDate startDate, LocalDate endDate, boolean includeStart, boolean includeEnd) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.includeStart = includeStart;
        this.includeEnd = includeEnd;
    }

    public DateRangeDTO(LocalDate startDate, boolean includeStart, LocalDate endDate, boolean includeEnd) {
        this.startDate = startDate;
        this.includeStart = includeStart;
        this.endDate = endDate;
        this.includeEnd = includeEnd;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isIncludeStart() {
        return includeStart;
    }

    public void setIncludeStart(boolean includeStart) {
        this.includeStart = includeStart;
    }

    public boolean isIncludeEnd() {
        return includeEnd;
    }

    public void setIncludeEnd(boolean includeEnd) {
        this.includeEnd = includeEnd;
    }
}
