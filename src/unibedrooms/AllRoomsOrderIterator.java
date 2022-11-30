package unibedrooms;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.OrderedDictionary;

public class AllRoomsOrderIterator implements Iterator<Room> {

    private OrderedDictionary<String,OrderedDictionary<String,Room>> localDictionary;

    private Iterator<Entry<String, OrderedDictionary<String, Room>>> localsIt;

    private Entry<String,OrderedDictionary<String,Room>> local;

    private Iterator<Entry<String, Room>> roomsIt;

    private Room nextToReturn;

    public AllRoomsOrderIterator(OrderedDictionary<String,OrderedDictionary<String,Room>> dic){
        localDictionary=dic;
        init();
    }

    private void init(){
        localsIt=localDictionary.iterator();
        while(localsIt.hasNext()){
            local = localsIt.next();
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
        localsIt.rewind();
        init();
    }

    private Room getNext(){
        if(roomsIt.hasNext())
            return roomsIt.next().getValue();
        else{
            Room next=null;
            while(localsIt.hasNext()) {
            /*if(localsIt.hasNext()){
                local=localsIt.next();
                roomsIt=local.getValue().iterator();
                return roomsIt.next().getValue();
            }*/
                local=localsIt.next();
                roomsIt=local.getValue().iterator();
                next=roomsIt.next().getValue();
                if(next!=null)
                    break;
            }
            return next;
        }

    }
}
