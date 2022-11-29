package unibedrooms;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;

public class AvailableRoomsIterator implements Iterator<Room> {

    Iterator<Entry<String, Room>> allRoomIterator;

    Entry<String, Room> nextToReturn;

    public  AvailableRoomsIterator(OrderedDictionary<String,Room> dic){
        allRoomIterator=dic.iterator();
    }

    @Override
    public boolean hasNext() {
        return nextToReturn!=null;
    }

    @Override
    public Room next() throws NoSuchElementException {
        return nextToReturn.getValue();
    }

    @Override
    public void rewind() {

    }

    private getNextFree(){

    }
}
