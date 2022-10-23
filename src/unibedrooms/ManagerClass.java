package unibedrooms;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class ManagerClass extends AbstractUser implements Manager {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;


    /**
     * the AbstractUser constructor
     *
     * @param login          - the managers's login
     * @param name           - the managers's name
     * @param universityName - the name of the university that the manager belongs to
     */
    protected ManagerClass(String login, String name, String universityName) {
        super(login, name, universityName);
    }
}
