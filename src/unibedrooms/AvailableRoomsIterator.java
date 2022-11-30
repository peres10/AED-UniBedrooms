package unibedrooms;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;
import exceptions.NoRoomsInLocationException;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class AvailableRoomsIterator implements Iterator<Room> {

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 0L;

    /**
     * Iterator of the dictionary
     */
    Iterator<Entry<String, Room>> allRoomIterator;

    /**
     * Dictionary of rooms
     */
    OrderedDictionary<String,Room> dic;

    /**
     * Next room to return
     */
    Room nextToReturn;

    /**
     * Iterator of the available rooms in a location
     *
     * @param dic - dictionary of rooms
     * @throws NoRoomsInLocationException - if there are no room in the location specified
     */
    public  AvailableRoomsIterator(OrderedDictionary<String,Room> dic) throws NoRoomsInLocationException {
        this.dic = dic;
        init();
    }

    private void init() throws NoRoomsInLocationException{
        allRoomIterator=dic.iterator();
        nextToReturn=getNextFree();
        if(nextToReturn==null)
            throw new NoRoomsInLocationException();
    }

    @Override
    public boolean hasNext() {
        return nextToReturn!=null;
    }

    @Override
    public Room next() throws NoSuchElementException {
        Room element = nextToReturn;
        nextToReturn = getNextFree();
        return element;
    }

    @Override
    public void rewind() {
        allRoomIterator.rewind();
        nextToReturn=getNextFree();
    }

    /**
     * Returns the next room in the iterator that is available
     *
     * @return - if there is an available room returns it, if not returns null
     */
    private Room getNextFree(){
        Room room=null;
        while(allRoomIterator.hasNext()){
            room = allRoomIterator.next().getValue();
            if(room.getEstado().equals("livre")){
                nextToReturn=room;
                break;
            }
            room = null;
        }
        return room;
    }
}
