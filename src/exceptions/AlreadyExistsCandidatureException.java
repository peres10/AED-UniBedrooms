package exceptions;

public class AlreadyExistsCandidatureException extends Exception {
	 /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final String errMsg = "Candidatura existente.";

    public AlreadyExistsCandidatureException() {
        super(errMsg);
    }
}
