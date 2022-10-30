package unibedrooms;


/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public interface Student extends User {

    /**
     * Gets the name of a student's local of residence
     *
     * @return name of a local
     */
    String getLocal();

    /**
     * Gets the age of the student
     *
     * @return age
     */
    int getAge();

	void removeApplication(RoomApplication roomApp);

	void removeAllApplicationsFromStudent();
}
