package dataStructures;

import javax.swing.*;
import java.util.Arrays;

/**
 * Separate Chaining Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleList<K,V>();
            //TOD: Original comentado para nao dar erro de compilacao
            //table[i] = null;
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() ) {
            //TOD: left as an exercise.
            //Original commented, to compile.
            this.rehash();
            //return null;
        }

        int index=this.hash(key);
        V valueInserted=table[index].insert(key,value);
        if(valueInserted==null)
            currentSize++;
        //TOD: Left as an exercise.
        return valueInserted;
    }

    @Override
    public V remove( K key )
    {
        //TOD: Left as an exercise.
        int index=this.hash(key);
        V value=table[index].remove(key);
        if(value!=null)
            currentSize--;
        return value;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        //TOD: Left as an exercise.
        return new SepChainIterator<>(table);
    }

    private void rehash(){


        int newCapacity = this.maxSize*2;
        int newArraySize = HashTable.nextPrime((int)(1.1 * newCapacity));
        Dictionary<K,V>[] newTable = (Dictionary<K,V>[]) new Dictionary[newArraySize];
        for ( int i = 0; i < newArraySize; i++ )
            newTable[i] = new OrderedDoubleList<K,V>();
        Dictionary<K,V> newDictionary;
        for(int i=0;i<table.length;i++){
            newDictionary=table[i];
            if(newDictionary!=null){
                Iterator<Entry<K,V>> it=newDictionary.iterator();
                while(it.hasNext()){
                    Entry<K,V> nextEntry = it.next();
                    if (nextEntry != null){
                        int index=Math.abs(nextEntry.getKey().hashCode()) % newTable.length;
                        newTable[index].insert(nextEntry.getKey(),nextEntry.getValue());
                    }
                }
            }
        }
        this.maxSize=newCapacity;
        this.table=newTable;

    }

    private Dictionary<K,V> getDictionary(){
        Dictionary<K,V> dictionary=new OrderedDoubleList<>();
        for(int i=0;i<table.length;i++){
            if (table[i] != null) {
                Iterator<Entry<K,V>> it=table[i].iterator();
                while(it.hasNext()){
                    Entry<K,V> nextEntry=it.next();
                    if(nextEntry!=null){
                        dictionary.insert(nextEntry.getKey(), nextEntry.getValue());
                    }
                }
            }
        }
        return dictionary;
    }
}
































