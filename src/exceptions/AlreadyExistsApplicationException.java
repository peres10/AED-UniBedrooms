package exceptions;

public class AlreadyExistsApplicationException extends Exception {
	 /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final String errMsg = "Candidatura existente.";

    public AlreadyExistsApplicationException() {
        super(errMsg);
    }
}
