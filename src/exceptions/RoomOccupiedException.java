package exceptions;

public class RoomOccupiedException extends Exception {
	 /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final String errMsg = "Quarto ocupado.";

    public RoomOccupiedException() {
        super(errMsg);
    }
}
