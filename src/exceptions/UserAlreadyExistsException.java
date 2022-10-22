package exceptions;

public class UserAlreadyExistsException extends Exception{

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final String errMsg = "Utilizador ja existente.";

    public UserAlreadyExistsException(){
        super(errMsg);
    }

}
