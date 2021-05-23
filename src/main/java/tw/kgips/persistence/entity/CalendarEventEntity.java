package tw.kgips.persistence.entity;

import tw.kgips.constant.EventType;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "calendar_event")
public class CalendarEventEntity {

    @Id
    @Column(name = "sn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;

    @Column(name = "event_type")
    private EventType eventType;

    @Column(name = "date")
    private OffsetDateTime date;

    public Long getSn() {
        return sn;
    }

    public void setSn(Long sn) {
        this.sn = sn;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }
}
