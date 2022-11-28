package unibedrooms;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import exceptions.ActiveApplicationException;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class RoomClass implements Room {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * Possible states of the rooms (free or occupied)
     */
    static final String stateFree="livre";
    static final String stateOccupied="ocupado";

    /**
     * A room's code
     */
    private final String code;

    /**
     * The name of the room's residence
     */
    private final String residence;

    /**
     * The name of the room's university
     */
    private final String university;

    /**
     * The local of the room
     */
    private final String local;

    /**
     * The room's floor
     */
    private final int floor;

    /**
     * The descritption of the room
     */
    private final String description;

    /**
     * The state of the room
     */
    private String state;

    /**
     * The manager of the room
     */
    private Manager manager;
    
    private Student studentResident;

    /**
     * List of candidatures to the room
     */
    DoubleList<Student> studentsApplying;

    /**
     *
     * @param code - the room's code
     * @param residence - the name of the room's residence
     * @param universityName - the name of the room's university
     * @param local - the local of the room
     * @param floor - the floor of the room
     * @param description - the description of the room
     */
    public RoomClass(String code, String residence, String universityName, String local, int floor, String description,Manager manager) {
        this.code=code;
        this.residence=residence;
        this.university=universityName;
        this.local=local;
        this.floor=floor;
        this.description=description;
        this.state=stateFree;
        this.manager=manager;
        this.studentsApplying=new DoubleList<>();
        this.studentResident = null;
    }

    @Override
    public String getRoomCode() {
        return code;
    }

    @Override
    public String getResidence() {
        return residence;
    }

    @Override
    public String getUniversityName() {
        return university;
    }

    @Override
    public String getLocal() {
        return local;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getEstado() {
        return state;
    }

    @Override
    public String getManagerLogin() {
        return manager.getLogin();
    }

    @Override
    public void modifyState(String newState) throws ActiveApplicationException {
        if(newState.equals(stateOccupied) && !studentsApplying.isEmpty())
            throw new ActiveApplicationException();
        else
            state=newState;
    }
    
    @Override
    public boolean hasRoomApplication() {
        return !studentsApplying.isEmpty();
    }

	@Override
	public boolean studentHasRoomApplication(User student) {
		Iterator<Student> studentAppIT = studentsApplying.iterator();
		Student studentApp;
		while(studentAppIT.hasNext()) {
			studentApp = studentAppIT.next();
			if(studentApp.getLogin().equals(student.getLogin()))
				return true;
		}
		return false;
	}

	@Override
	public void addRoomApplication(Student studentApplying) {
		this.studentsApplying.addLast(studentApplying);
	}

	@Override
	public void acceptApplication(User student) {
		this.state = stateOccupied;
		this.studentResident = (Student)student;

		studentResident.removeAllApplicationsFromStudent();
		removeApplicationFromStudents();
		removeAllApplications();
	}

	@Override
	public void removeApplicationFromStudent(Student student) {
		for(int i = 0; i < this.studentsApplying.size(); i++) {
			if(this.studentsApplying.get(i) == student) {
				this.studentsApplying.remove(i);
				return;
			}
		}
	}

	@Override
	public Iterator<Student> getApplicationsIt() {
		return studentsApplying.iterator();
	}

	/**
	 * Removes the application from the students
	 */
	private void removeApplicationFromStudents() {
		Iterator<Student> studentAppIT = studentsApplying.iterator();
		Student studentApp;
		while(studentAppIT.hasNext()) {
			studentApp = studentAppIT.next();
			studentApp.removeApplication(this);
		}
	}
	
	/**
	 * Removes all applications
	 */
	private void removeAllApplications() {	
		while(studentsApplying.size() != 0)
			studentsApplying.removeLast();
	}
	
	
}
