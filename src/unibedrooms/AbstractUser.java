package unibedrooms;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public abstract class AbstractUser implements User{

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * A user's login
     */
    private final String login;

    /**
     * A user's name
     */
    private final String name;

    /**
     * A user's university
     */
    private final String university;
    //TODO trocar isto pela univerisdade mesmo quando a classe for implementada

    /**
     * the AbstractUser constructor
     *
     * @param login - the user's login
     * @param name - the user's name
     * @param universityName - the user's university name
     */
    protected AbstractUser(String login, String name, String universityName){
        this.login = login;
        this.name = name;
        this.university = universityName;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUniversityName() {
        return university;
    }
}
