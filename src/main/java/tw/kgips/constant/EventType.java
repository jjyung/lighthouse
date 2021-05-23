package tw.kgips.constant;

public enum EventType {
    DAY_OFF(1),
    TW_FUTURE_SETTLEMENT(2),
    JPMORGAN_SETTLEMENT(3);

    int code;

    EventType(int code) {
        this.code = code;
    }

    public static EventType valueOf(Integer code) {
        for (EventType eventType : EventType.values()) {
            if (eventType.code == code) {
                return eventType;
            }
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
