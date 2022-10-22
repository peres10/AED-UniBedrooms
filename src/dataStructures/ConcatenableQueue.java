package dataStructures;

/**
 * ConcatenableQueue interface
 * @author Alexandre Peres 61615 am.peres@campus.fct.unl.pt
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public interface ConcatenableQueue<E> extends Queue<E> {
    //Removes all of the elements of the elements from the specified queue and
    //inserts them at the end of the queue (in proper order)
    void append( ConcatenableQueue<E> queue);
}
