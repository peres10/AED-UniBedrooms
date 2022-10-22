package dataStructures;

/**
 * ConcatenableQueue class
 * @author Alexandre Peres 61615 am.peres@campus.fct.unl.pt
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class ConcatenableQueueClass<E> extends QueueInList<E> implements ConcatenableQueue<E>{

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    public ConcatenableQueueClass(){
        super();
    }

    @Override
    public void append(ConcatenableQueue<E> queue){
        while(!queue.isEmpty())
            this.enqueue(queue.dequeue());
    }
}
