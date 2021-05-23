package tw.kgips.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import tw.kgips.persistence.entity.CalendarEventEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Transactional
public interface CalendarEventRepository extends JpaRepository<CalendarEventEntity, Integer> {
    List<CalendarEventEntity> findAllByDateGreaterThanEqualAndDateLessThan(OffsetDateTime start, OffsetDateTime end);
}
