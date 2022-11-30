package unibedrooms;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;


/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class AllRoomsOrderIterator implements Iterator<Room>  {

    /**
     * Serial Version UID of the class
     */
    private static final long serialVersionUID = 0L;


    /**
     * The Dictionary of locations that contain the Dictionaries of Rooms
     */
    private final OrderedDictionary<String,OrderedDictionary<String,Room>> localDictionary;

    /**
     * The Iterator of the Dictionary of locations
     */
    private Iterator<Entry<String, OrderedDictionary<String, Room>>> locationsIt;

    /**
     * The current local that is being iterated
     */
    private Entry<String,OrderedDictionary<String,Room>> local;

    /**
     * Iterator of rooms
     */
    private Iterator<Entry<String, Room>> roomsIt;

    /**
     * Next room to return
     */
    private Room nextToReturn;

    /**
     * Iterator of all the rooms sorted by location
     *
     * @param dic - dictionary locations that contains dictionaries of rooms
     */
    public AllRoomsOrderIterator(OrderedDictionary<String,OrderedDictionary<String,Room>> dic){
        localDictionary=dic;
        init();
    }

    private void init(){
        locationsIt=localDictionary.iterator();
        while(locationsIt.hasNext()){
            local = locationsIt.next();
            OrderedDictionary<String,Room> rooms = local.getValue();
            roomsIt = rooms.iterator();
            if (roomsIt.hasNext()) {
                nextToReturn = roomsIt.next().getValue();
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextToReturn!=null;
    }

    @Override
    public Room next() throws NoSuchElementException {
        Room element=nextToReturn;
        nextToReturn=getNext();
        return element;
    }

    @Override
    public void rewind() {
        locationsIt.rewind();
        init();
    }

    /**
     * Searches the next Room in the current location, if there's one,
     * if there isn't it goes to the next locations and tries to find it
     *
     * @return - if next room exists, returns it, if not returns null
     */
    private Room getNext(){
        if(roomsIt.hasNext())
            return roomsIt.next().getValue();
        else{
            Room next=null;
            while(locationsIt.hasNext()) {
                local=locationsIt.next();
                if(!local.getValue().isEmpty()) {
                    roomsIt = local.getValue().iterator();
                    next = roomsIt.next().getValue();
                    if (next != null)
                        break;
                }
            }
            return next;
        }

    }
}
