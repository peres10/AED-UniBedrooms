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
     * @param nome - a manager's name
     * @param universidade - the name of the university that the manager belongs to
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
}
