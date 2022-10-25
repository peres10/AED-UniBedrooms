package unibedrooms;

import dataStructures.DoubleList;

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

    private RoomApplication[] roomApplications;
    
    private int applicationCount;
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
        this.roomApplications = new RoomApplicationClass[10];
        this.applicationCount = 0;
    }

    @Override
    public String getLocal() {
        return local;
    }

    @Override
    public int getAge() {
        return age;
    }
    
    public void addRoomApplication(RoomApplication application) {
    	if(applicationCount < 10) {
    		this.roomApplications[applicationCount] = application;
    		applicationCount++;
    	}
    }
    
    public int getNumberApplications() {
    	return applicationCount;
    }

	public boolean hasApplicationToRoom(RoomApplication application) {
		for(int i = 0; i < applicationCount; i++) {
			if(roomApplications[i].getStudentName().equals(application.getStudentName()) && roomApplications[i].getRoomCode().equals(application.getRoomCode()))
				return true;
		}
			
		return false;
	}

}
