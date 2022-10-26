package unibedrooms;

public class RoomApplicationClass implements RoomApplication {
    static final long serialVersionUID = 0L;

	private final Room room;
	private final Student student;
	
	public RoomApplicationClass(Room room, Student student) {
		this.room = room;
		this.student = student;
	}
	
	public String getStudentName() {
		return student.getLogin();
	}
	
	public String getRoomCode() {
		return room.getRoomCode();
	}
}
