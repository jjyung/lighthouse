package tw.kgips.exception;

public class UnknownHtmlException extends RuntimeException {
    private static final long serialVersionUID = -7216005414374129149L;

    public UnknownHtmlException(String message) {
        super(message);
    }
}
