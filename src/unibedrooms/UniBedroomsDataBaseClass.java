package unibedrooms;

import dataStructures.DoubleList;
import dataStructures.Iterator;
import exceptions.*;


/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class UniBedroomsDataBaseClass implements UniBedroomsDataBase {

    /**
     * Serial Version UID of the class
     */
    static final long serialVersionUID = 0L;

    /**
     * The users in the database
     */
    private DoubleList<User> users;

    /**
     * The rooms in the database
      */
    private DoubleList<Room> rooms;

    static final String stateOccupied="ocupado";
    
    /**
     * A Database that has a list of users and a list of rooms
     */
    public UniBedroomsDataBaseClass(){
        users = new DoubleList<User>();
        rooms = new DoubleList<Room>();
    }


    @Override
    public void addStudent(String login, String name, int age, String local, String university) throws UserAlreadyExistsException {
        if(searchUser(login) != null)
            throw new UserAlreadyExistsException();
        else{
            users.addLast(new StudentClass(login,name,university,age,local));
        }
    }

    @Override
	public void addManager(String login, String name, String university) throws UserAlreadyExistsException {
	    if(searchUser(login) != null)
	        throw new UserAlreadyExistsException();
	    else{
	        users.addLast(new ManagerClass(login,name,university));
	    }
	}


	@Override
	public void addRoom(String code, String login, String nameResidence, String universityName, String local, int floor, String description) throws RoomAlreadyExistsException, ManagerDoesNotExistException, NonAuthorizedOperationException {
		if(searchRoom(code) != null)
	        throw new RoomAlreadyExistsException();
		Manager manager = getManager(login);
	    if(!managerFromUniversity(manager,universityName))
	        throw new NonAuthorizedOperationException();
	    else{
	        rooms.addLast(new RoomClass(code,nameResidence,universityName,local,floor,description, manager));
	    }
	}


	@Override
	public void insertApplication(String login, String code) throws StudentDoesNotExistException, NonAuthorizedOperationException, RoomDoesNotExistException, RoomOccupiedException, AlreadyExistsCandidatureException {
	    Student student = getStudent(login);
		if(((StudentClass)student).getNumberApplications() == 10)
	        throw new NonAuthorizedOperationException();
		
	    Room room = getRoom(code);
		if(room.getEstado().equals(stateOccupied))
			throw new RoomOccupiedException();
		
		RoomApplication application = new RoomApplicationClass(room, (StudentClass)student);
		if(((StudentClass)student).hasApplicationToRoom(application))
	        throw new AlreadyExistsCandidatureException();
		
		room.addRoomApplication(application);
		((StudentClass)student).addRoomApplication(application);
	}


	@Override
    public Student getStudent(String login) throws StudentDoesNotExistException {
        User student = searchUser(login);
        if(!(student instanceof Student))
            throw new StudentDoesNotExistException();
        else{
            return (Student) student;
        }
    }

    @Override
    public Manager getManager(String login) throws ManagerDoesNotExistException {
        User manager = searchUser(login);
        if(!(manager instanceof Manager))
            throw new ManagerDoesNotExistException();
        else{
            return (Manager) manager;
        }
    }

    @Override
    public Room getRoom(String code) throws RoomDoesNotExistException {
        Room room = searchRoom(code);
        if(room == null)
            throw new RoomDoesNotExistException();
        else
            return room;
    }

    @Override
    public void updateRoomState(String code, String loginManager, String newState) throws RoomDoesNotExistException, NonAuthorizedOperationException, ActiveApplicationException {
        Room room = getRoom(code);
        if(!room.getManagerLogin().equals(loginManager))
            throw new NonAuthorizedOperationException();
        room.modifyState(newState);
    }

    @Override
    public void removeRoom(String code, String loginManager) throws RoomDoesNotExistException, NonAuthorizedOperationException, ActiveApplicationException {
        Room room = getRoom(code);
        if(!room.getManagerLogin().equals(loginManager))
            throw new NonAuthorizedOperationException();
        if(room.hasRoomApplication())
            throw new ActiveApplicationException();
        else
            rooms.remove(room);
    }

	@Override
	public void acceptApplication(String code, String loginManager, String loginStudent) throws RoomDoesNotExistException, NonAuthorizedOperationException, ApplicationDoesNotExistException {
		Room room = getRoom(code);
		if(!room.getManagerLogin().equalsIgnoreCase(loginManager))
			throw new NonAuthorizedOperationException();
		User student = searchUser(loginStudent);
		if(student == null || !hasApplicationToRoom(student, room))
			throw new ApplicationDoesNotExistException();
		room.acceptApplication(student);
	}


	@Override
	public Iterator<RoomApplication> listApplications(String code, String loginManager) throws RoomDoesNotExistException, NonAuthorizedOperationException, NoApplicationsToRoomException {
		Room room = getRoom(code);
		if(!room.getManagerLogin().equalsIgnoreCase(loginManager))
			throw new NonAuthorizedOperationException();
		if(!room.hasRoomApplication())
			throw new NoApplicationsToRoomException();
		
		return room.getApplicationsIt();
		
		
	}


	/**
	 * Searches for a user in the list of users and returns it
	 *  
	 * @param login - login of a student
	 * @return - if student exists returns it, if not returns null
	 */
    private User searchUser(String login){
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getLogin().equalsIgnoreCase(login))
                return user;
        }
        return null;
    }
    
    /**
     * Searches for a room in the list of rooms and returns it
     * 
     * @param roomCode - code of a room
     * @return - if room exists returns it, if not returns null
     */
    private Room searchRoom(String roomCode){
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()){
            Room room = it.next();
            if(room.getRoomCode().equalsIgnoreCase(roomCode))
                return room;
        }
        return null;
    }

    /**
     * Checks if the manager is from the university
     * 
     * @param manager
     * @param universityName
     * @return
     */
    private boolean managerFromUniversity(User manager, String universityName){
        return manager.getUniversityName().equalsIgnoreCase(universityName);
    }


	/**
	 * Checks if there is an application in the room
	 * 
	 * @param student - a student
	 * @param room - a room
	 * @return - true if application exists, false if not
	 */
	private boolean hasApplicationToRoom(User student, Room room) {
		if(room.studentHasRoomApplication(student))
			return true;
		return false;
	}
}	
