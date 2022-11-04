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

    private DoubleList<RoomApplication> roomApplications;
    
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
        this.roomApplications = new DoubleList<>();
    }

    @Override
    public String getLocal() {
        return local;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getNumberApplications() {
		return roomApplications.size();
	}

    @Override
	public boolean hasApplicationToRoom(Room room) {
		for(int i = 0; i < roomApplications.size(); i++) {
			if(roomApplications.get(i).getRoom() == room)
				return true;
		}
			
		return false;
	}
    
    @Override
	public void addRoomApplication(RoomApplication application) {
		if(roomApplications.size() < 10) {
			this.roomApplications.addLast(application);;
		}
	}

	@Override
	public void removeApplication(RoomApplication roomApp) {
		for(int i = 0; i < this.roomApplications.size(); i++) {
			if(this.roomApplications.get(i) == roomApp) {
				this.roomApplications.remove(i);
				return;
			}
		}
	}

	@Override
	public void removeAllApplicationsFromStudent() {
		RoomApplication roomApp;
		while(this.roomApplications.size() != 0) {
			roomApp = this.roomApplications.getFirst();
			roomApp.getRoom().removeApplicationFromStudent(this);
			this.roomApplications.removeFirst();
		}
	}
}
