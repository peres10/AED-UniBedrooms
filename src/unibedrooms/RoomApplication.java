package unibedrooms;

import java.io.Serializable;

public interface RoomApplication extends Serializable{
	
	/**
	 * Returns the student of the application
	 * 
	 * @return - the student
	 */
	public Student getStudent();

	/**
	 * Returns the room of the application
	 * 
	 * @return - the room
	 */
	public Room getRoom();
}
