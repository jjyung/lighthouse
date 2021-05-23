package tw.kgips.persistence.converter;

import tw.kgips.constant.EventType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class EventTypeConverter implements AttributeConverter<EventType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EventType archiveFileType) {
        return Optional.ofNullable(archiveFileType)
            .map(EventType::getCode)
            .orElse(null);
    }

    @Override
    public EventType convertToEntityAttribute(Integer archiveFileType) {
        return EventType.valueOf(archiveFileType);
    }
}
