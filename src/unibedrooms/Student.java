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

    /**
     * Gets the number of application
     * 
     * @return number of applications
     */
    int getNumberApplications();

    /**
     * Checks if has application
     * 
     * @param application - the application
     * @return true if application exists, false if not
     */
	boolean hasApplicationToRoom(Room room);

	/**
	 * Adds a room application
	 * 
	 * @param application - the application
	 */
	void addRoomApplication(RoomApplication application);

	/**
     * Removes an application from the student
     * 
     * @param roomApp
     */
	void removeApplication(RoomApplication roomApp);
	
	/**
	 * Removes all applications from the student
	 */
	void removeAllApplicationsFromStudent();


}
