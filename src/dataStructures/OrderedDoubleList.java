package dataStructures;


/**
 * Doubly linked list Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <K, V> Generics - K extends Comparable
 * 
 */
public class OrderedDoubleList<K extends Comparable<K>, V>
	implements OrderedDictionary<K , V> {

    /**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
    
	/**
     *  Node at the head of the list.
     */
	protected DoubleListNode<Entry<K,V>> head;

    /**
     * Node at the tail of the list.
     */
	protected DoubleListNode<Entry<K,V>> tail;

    /**
     * Number of elements in the list.
     */
	protected int currentSize;
	
    /**
     * Constructor of an empty ordered double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
	public OrderedDoubleList() {
		head=null;
		tail=null;
		currentSize=0;
	}

    /**
     * Inserts the Entry element before node after.
     * Precondition: after is not the head of the ordered double list.
     * @param element - Entry to be inserted
     * @param after - Node to be next to the new node  
     */
	protected void addBeforeNode(Entry<K,V> element, DoubleListNode<Entry<K,V>> after){
		//TOD: Left as an exercise.
       // DoubleListNode<Entry<K,V>> prevNode = this.findNode(after.getElement().getKey()).getPrevious();
        //DoubleListNode<Entry<K,V>> nextNode = this.findNode(after.getElement().getKey());
        //DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element, prevNode, nextNode);
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element,after.getPrevious(),after);

        //prevNode.setNext(newNode);
        //nextNode.setPrevious(newNode);

        after.getPrevious().setNext(newNode);
        after.setPrevious(newNode);
        currentSize++;
	}
	
    /**
     * Inserts the Entry element at the first position in the list.
     * @param element - Entry to be inserted
     */
    protected void addFirst( Entry<K,V> element )
    {
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element, null, head);
        if ( this.isEmpty() )
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }


    /**
     * Inserts the Entry element at the last position in the list.
     * @param element - Entry to be inserted
     */
    protected void addLast( Entry<K,V> element )
    {
        //TOD: Left as an exercise.
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element, tail, null);
        if ( this.isEmpty() )
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

	@Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		//TOD: Left as an exercise.
        if(isEmpty())
            throw new EmptyDictionaryException();
        return tail.getElement();
	}

    @Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		//TOD: Left as an exercise.
        if(isEmpty())
            throw new EmptyDictionaryException();
        return head.getElement();
	}

    /**
     * Returns the node with the Entry with Key key
     * in the list, if the list contains this entry.
     * Otherwise, returns null.
     * @param key - Key of type K to be searched
     * @return DoubleListNode<E> where the Entry with key was found, or the one with the key immmediately after 
     */
	protected DoubleListNode<Entry<K,V>> findNode (K key){
		//TOD: Left as an exercise.
        DoubleListNode<Entry<K,V>> node = head;
        while(node != null && node.getElement().getKey().compareTo(key)!=0){
            node=node.getNext();
        }
        return node;
	}
	
    @Override
	public V find(K key) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		//TOD: Left as an exercise.
        V value=null;
        if(node!=null){
            value=node.getElement().getValue();
        }
        return value;
	}


	@Override
	public V insert(K key, V value) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
        V returnValue=null;
		if ((node!=null) && (node.getElement().getKey().compareTo(key)==0)){
            //TODO: Left as an exercise.
            returnValue = node.getElement().getValue();
            ((EntryClass)node.getElement()).setValue(value);
		}
		else {
		  Entry<K,V> newEntry=new EntryClass<K,V> (key, value);
            if(currentSize!=0) {
                node=head;
                while (node != null && !(node.getElement().getKey().compareTo(key) > 0)) {
                    node = node.getNext();
                }
                if (node == head) {
                    addFirst(newEntry);
                } else if (node == null) {
                    addLast(newEntry);
                } else {
                    addBeforeNode(newEntry, node);
                }
            }
            else {
                addFirst(newEntry);
            }
          }
		  //recommended: re-use addFirst, addLast and addBeforeNode
          //TODO: Left as an exercise.
        //return null;
        return returnValue;
	}
	
	@Override
    public boolean isEmpty() {
		return currentSize==0;
	}

    @Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<Entry<K,V>>(head,tail);
	}

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode( )
    {
        head = head.getNext();
        if ( head == null )
            tail = null;
        else
            head.setPrevious(null);
        currentSize--;
    }


    /**
     * Removes and returns the value at the first entry in the list.
     */
    protected V removeFirst( ) throws EmptyDictionaryException
    {
        //TODO: Left as an exercise.
        if ( this.isEmpty() )
            throw new EmptyDictionaryException();

        V value = head.getElement().getValue();
        this.removeFirstNode();
        return value;

        /*if ( this.isEmpty() )
            throw new EmptyDictionaryException();

        V value = tail.getElement().getValue();
        this.removeLastNode();
        return value;*/
    }


    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode( )
    {
        //TODO: Left as an exercise.
        tail = tail.getPrevious();
        if(tail == null)
            head = null;
        else
            tail.setNext(null);
        currentSize--;
    }


    /**
     * Removes and returns the value at the last entry in the list.
     */
    protected V removeLast( ) throws EmptyDictionaryException
    {
        if ( this.isEmpty() )
            throw new EmptyDictionaryException();

        V value = tail.getElement().getValue();
        this.removeLastNode();
        return value;
    }

    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode( DoubleListNode<Entry<K,V>> node )
    {
        //TODO: Left as an exercise.
        DoubleListNode<Entry<K,V>> prevNode = node.getPrevious();
        DoubleListNode<Entry<K,V>> nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        currentSize--;
    }

    @Override
    public V remove(K key) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		if ((node == null) || (node.getElement().getKey().compareTo(key)!=0))
			return null;
		else {
            //TODO: Left as an exercise.
            if(node==head)
                return this.removeFirst();
            else if(node==tail)
                return this.removeLast();
            else{
                this.removeMiddleNode(node);
                return node.getElement().getValue();
            }
        }
	}

    @Override
	public int size() {
		return currentSize;
	}
	
	
}
