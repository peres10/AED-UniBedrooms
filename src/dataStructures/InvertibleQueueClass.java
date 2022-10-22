package dataStructures;

public class InvertibleQueueClass<E> extends QueueInList<E> implements InvertibleQueue<E> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    public InvertibleQueueClass(){
        super();
    }

    @Override
    public void invert(){
        recursiveFlip();
    }

    private void recursiveFlip(){
        E buffer = this.dequeue();
        if(!this.isEmpty()){
            recursiveFlip();
        }
        this.enqueue(buffer);
    }

}
