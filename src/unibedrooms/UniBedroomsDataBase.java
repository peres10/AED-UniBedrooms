package unibedrooms;

import exceptions.*;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public interface UniBedroomsDataBase extends Serializable {

    /**
     * Adds a student to the system
     *
     * @param login - a student's login
     * @param name - a student's name
     * @param age - a student's age
     * @param local - the name of the student's residence local
     * @param university - the name of the student's university
     * @throws UserAlreadyExistsException - if there is someone with the same login already registered
     */
    void addStudent(String login,String name,int age,String local,String university) throws UserAlreadyExistsException;

    /**
     * Gets a student registered in the system
     *
     * @param login - a student's login
     * @return - a student
     * @throws StudentDoesNotExistException - if a student with a given login does not exist in the system
     */
    Student getStudent(String login) throws StudentDoesNotExistException;

    /**
     * Adds a manager to the system
     *
     * @param login - a manager's login
     * @param name - a manager's name
     * @param university - the name of the university that the manager belongs to
     * @throws UserAlreadyExistsException - if there is someone with the same login already registered
     */
    void addManager(String login, String name, String university) throws UserAlreadyExistsException;

    /**
     * Gets a manager registered in the system
     *
     * @param login - a manager's login
     * @return - a manager
     * @throws ManagerDoesNotExistException - if a manager with a given login does not exist in the system
     */
    Manager getManager(String login) throws ManagerDoesNotExistException;


    /**
     * Adds a room to the system
     *
     * @param code - a room's code
     * @param login - the login of manager
     * @param nameResidence - the name of the residence
     * @param universityName - the name of the university
     * @param local - the location of the room
     * @param floor - the floor of the room
     * @param description - the description of the room
     * @throws RoomAlreadyExistsException - if exists already a room in the system with the same name
     * @throws ManagerDoesNotExistException - if a manager with a given login does not exist in the system
     * @throws NonAuthorizedOperationException - if a manager does not belong to the same university as the room
     */
    void addRoom(String code, String login, String nameResidence, String universityName, String local, int floor, String description) throws RoomAlreadyExistsException, ManagerDoesNotExistException, NonAuthorizedOperationException;

    /**
     * Gets a room registered in the system
     *
     * @param code - a code of a room
     * @return - a room
     * @throws RoomDoesNotExistException - if a room with the given code does not exist in the system
     */
    Room getRoom(String code) throws RoomDoesNotExistException;

    /**
     * Modifies the state of a room
     *
     * @param code - a code of a room
     * @param loginManager - the login of a manager
     * @throws RoomDoesNotExistException - if a room with the given code does not exist
     * @throws NonAuthorizedOperationException - if a manager does not belong to the same university as the room
     * @throws ActiveCandidaturesException - if the new state is "ocupado" and room still has active candidatures
     */
    void updateRoomState(String code, String loginManager,String newState) throws RoomDoesNotExistException,NonAuthorizedOperationException,ActiveCandidaturesException;
}
