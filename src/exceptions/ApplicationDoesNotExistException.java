package exceptions;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class ApplicationDoesNotExistException extends Exception{

	   /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    private static final String errMsg = "Inexistencia da candidatura referida.";

    public ApplicationDoesNotExistException(){
        super(errMsg);
    }

}

