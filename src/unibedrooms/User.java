package unibedrooms;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public interface User extends Serializable {

    /**
     * Gets a user's login
     *
     * @return login
     */
    String getLogin();

    /**
     * Gets a user's name
     * @return name
     *
     */
    String getName();

    /**
     * Gets the user's university name
     * @return a university name
     *
     */
    String getUniversityName();
}
