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
     * @return
     */
    String getRoomCode();
}
