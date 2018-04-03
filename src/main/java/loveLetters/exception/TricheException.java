package loveLetters.exception;

public class TricheException extends LoveLettersException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TricheException(String message, Throwable cause) {
        super("[Triche : " + message + "]", cause);
        // TODO Auto-generated constructor stub
    }

    public TricheException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TricheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public TricheException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public TricheException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
