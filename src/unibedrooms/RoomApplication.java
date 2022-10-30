package unibedrooms;

import java.io.Serializable;

public interface RoomApplication extends Serializable{
	public Student getStudent();

	public Room getRoom();
}
