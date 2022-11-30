package unibedrooms;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;
import exceptions.NoRoomsInLocalidadeException;

public class AvailableRoomsIterator implements Iterator<Room> {

    Iterator<Entry<String, Room>> allRoomIterator;
    OrderedDictionary<String,Room> dic;

    Room nextToReturn;

    public  AvailableRoomsIterator(OrderedDictionary<String,Room> dic) throws NoRoomsInLocalidadeException {
        this.dic = dic;
        init();
    }

    private void init() throws NoRoomsInLocalidadeException{
        allRoomIterator=dic.iterator();
        nextToReturn=getNextFree();
        if(nextToReturn==null)
            throw new NoRoomsInLocalidadeException();
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
