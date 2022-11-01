package unibedrooms;

public class RoomApplicationClass implements RoomApplication {
    
	/**
     * Serial Version UID of the class
     */
	static final long serialVersionUID = 0L;

	/**
	 * The room
	 */
	private final Room room;
	
	/**
	 * The student
	 */
	private final Student student;
	
	/**
	 * 
	 * @param room - the room
	 * @param student - the student
	 */
	public RoomApplicationClass(Room room, Student student) {
		this.room = room;
		this.student = student;
	}

	@Override
	public Student getStudent() {
		return student;
	}

	@Override
	public Room getRoom() {
		return room;
	}
	
	
}
