package dataStructures;

public class EntryClass<K,V> implements Entry<K,V> {

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

    protected void setValue(V newValue) {
        this.value=newValue;
    }

    protected void setKey(K newKey) {
        this.key=newKey;
    }
}
