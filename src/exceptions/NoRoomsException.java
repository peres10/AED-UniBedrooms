package exceptions;

public class NoRoomsException extends Exception {
	 /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final String errMsg = "Inexistencia de quartos.";

    public NoRoomsException() {
        super(errMsg);
    }
}
