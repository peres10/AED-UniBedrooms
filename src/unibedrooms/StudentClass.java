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

	/**
	 * List of rooms that a student applied to
	 */
    private List<Room> appliedRooms;
    
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
        this.appliedRooms = new DoubleList<>();
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
		return appliedRooms.size();
	}

    @Override
	public boolean hasApplicationToRoom(Room room) {
		for(int i = 0; i < appliedRooms.size(); i++) {
			if(appliedRooms.get(i) == room)
				return true;
		}
			
		return false;
	}
    
    @Override
	public void addRoomApplication(Room roomToApply) {
		if(appliedRooms.size() < 10) {
			this.appliedRooms.addLast(roomToApply);
		}
	}

	@Override
	public void removeApplication(Room roomToApply) {
		for(int i = 0; i < this.appliedRooms.size(); i++) {
			if(this.appliedRooms.get(i) == roomToApply) {
				this.appliedRooms.remove(i);
				return;
			}
		}
	}

	@Override
	public void removeAllApplicationsFromStudent() {
		Room roomToApply;
		while(this.appliedRooms.size() != 0) {
			roomToApply = this.appliedRooms.getFirst();
			roomToApply.removeApplicationFromStudent(this);
			this.appliedRooms.removeFirst();
		}
	}
}
