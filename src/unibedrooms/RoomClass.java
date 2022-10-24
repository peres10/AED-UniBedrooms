package unibedrooms;

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
     *
     * @param code - the room's code
     * @param residence - the name of the room's residence
     * @param universityName - the name of the room's university
     * @param local - the local of the room
     * @param floor - the floor of the room
     * @param description - the description of the room
     */
    public RoomClass(String code, String residence, String universityName, String local, int floor, String description) {
        this.code=code;
        this.residence=residence;
        this.university=universityName;
        this.local=local;
        this.floor=floor;
        this.description=description;
    }

    @Override
    public String getRoomCode() {
        return code;
    }
}
