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
    public Student getStudent(String login) throws StudentDoesNotExistException {
        User student = searchUser(login);
        if(!(student instanceof Student))
            throw new StudentDoesNotExistException();
        else{
            return (Student) student;
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
    public Manager getManager(String login) throws ManagerDoesNotExistException {
        User manager = searchUser(login);
        if(!(manager instanceof Manager))
            throw new ManagerDoesNotExistException();
        else{
            return (Manager) manager;
        }
    }

    @Override
    public void addRoom(String code, String login, String nameResidence, String universityName, String local, int floor, String description) throws RoomAlreadyExistsException, ManagerDoesNotExistException, NonAuthorizedOperationException {
        User manager = getManager(login);
        if(searchRoom(code) != null)
            throw new RoomAlreadyExistsException();
        if(!managerFromUniversity(manager,universityName))
            throw new NonAuthorizedOperationException();
        else{
            rooms.addLast(new RoomClass(code,nameResidence,universityName,local,floor,description));
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


    private User searchUser(String login){
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    private Room searchRoom(String roomCode){
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()){
            Room room = it.next();
            if(room.getRoomCode().equalsIgnoreCase(roomCode))
                return room;
        }
        return null;
    }

    private boolean managerFromUniversity(User manager, String universityName){
        return manager.getUniversityName().equals(universityName);
    }
}
