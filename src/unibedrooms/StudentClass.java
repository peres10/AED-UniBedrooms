package unibedrooms;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class StudentClass extends AbstractUser implements Student{

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * A student's age
     */
    private final int age;

    /**
     * A student's local of residence
     */
    private final String local;


    /**
     * the StudentClass constructor
     *
     * @param login          - the student's login
     * @param name           - the student's name
     * @param universityName - the student's university name
     * @param age            - the student's age
     * @param local          - the student's local of residence
     */
    protected StudentClass(String login, String name, String universityName,int age,String local) {
        super(login, name, universityName);
        this.age=age;
        this.local=local;
    }

    @Override
    public String getLocal() {
        return local;
    }

    @Override
    public int getAge() {
        return age;
    }

}
