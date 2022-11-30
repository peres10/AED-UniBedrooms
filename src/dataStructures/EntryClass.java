package dataStructures;

/**
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 */
public class EntryClass<K,V> implements Entry<K,V>,SetEntry<K,V> {

    private K key;

    private V value;

    public EntryClass(K key,V value){
        this.key=key;
        this.value=value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V newValue) {
        this.value=newValue;
    }

    @Override
    public void setKey(K newKey) {
        this.key=newKey;
    }
}
