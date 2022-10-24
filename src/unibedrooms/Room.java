package unibedrooms;

import exceptions.ActiveCandidaturesException;

import java.io.Serializable;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public interface Room extends Serializable {

    /**
     * Gets the code of the room
     *
     * @return - the code of the room
     */
    String getRoomCode();

    /**
     * Gets the name of the residence of the room
     *
     * @return - name of a residence
     */
    String getResidence();

    /**
     * Gets the name of the university the room belongs to
     *
     * @return - name of university
     */
    String getUniversityName();

    /**
     * Gets the local where the room belongs to
     *
     * @return - local
     */
    String getLocal();

    /**
     * Gets the floor of the room
     *
     * @return - number of the floor
     */
    int getFloor();

    /**
     * Gets the description of the room
     *
     * @return - description of the room
     */
    String getDescription();

    /**
     * Returns if the room is occupied or free
     *
     * @return - state of the room ("livre" or "ocupado")
     */
    String getEstado();

    /**
     * Returns the login of the manager of the room
     *
     * @return - a login of a manager
     */
    String getManagerLogin();

    //
    /**
     * Returns the number of candidatures to the room
     *
     * @return - number of candidatures
     */
    //int getNumberOfCandidatures();
    //

    /**
     * Modifies the state of a room
     *
     * @param newState - the new state of the room
     * @throws ActiveCandidaturesException - if the new state is "ocupado" and room still has active candidatures
     */
    void modifyState(String newState) throws ActiveCandidaturesException;
}
