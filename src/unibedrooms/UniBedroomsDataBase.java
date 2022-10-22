package unibedrooms;

import exceptions.UserAlreadyExistsException;

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

}
