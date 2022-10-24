package unibedrooms;

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
}
