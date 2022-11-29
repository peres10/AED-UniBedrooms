package unibedrooms;

import dataStructures.DoubleList;
import dataStructures.List;

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

    private List<Room> roomsToApply;
    
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
        this.roomsToApply = new DoubleList<>();
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
		return roomsToApply.size();
	}

    @Override
	public boolean hasApplicationToRoom(Room room) {
		for(int i = 0; i < roomsToApply.size(); i++) {
			if(roomsToApply.get(i) == room)
				return true;
		}
			
		return false;
	}
    
    @Override
	public void addRoomApplication(Room roomToApply) {
		if(roomsToApply.size() < 10) {
			this.roomsToApply.addLast(roomToApply);;
		}
	}

	@Override
	public void removeApplication(Room roomToApply) {
		for(int i = 0; i < this.roomsToApply.size(); i++) {
			if(this.roomsToApply.get(i) == roomToApply) {
				this.roomsToApply.remove(i);
				return;
			}
		}
	}

	@Override
	public void removeAllApplicationsFromStudent() {
		Room roomToApply;
		while(this.roomsToApply.size() != 0) {
			roomToApply = this.roomsToApply.getFirst();
			roomToApply.removeApplicationFromStudent(this);
			this.roomsToApply.removeFirst();
		}
	}
}
