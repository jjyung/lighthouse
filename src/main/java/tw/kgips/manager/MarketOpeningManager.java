package tw.kgips.manager;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;

@Component
public class MarketOpeningManager {

    public boolean isMarketOpening(OffsetDateTime date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        boolean isWorkingDays = dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
        // TODO special day-off
        return isWorkingDays;
    }

}
