package dataStructures;

public class SepChainIterator<K, V> implements Iterator<Entry<K,V>> {
    private static final long serialVersionUID=1L;

    private Dictionary<K, V>[] table;

    private Iterator<Entry<K,V>> it;

    private int counter;

    public SepChainIterator(Dictionary<K,V>[] table){
        this.table=table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        while(!it.hasNext()){
            if(counter<table.length-1)
                it=table[++counter].iterator();
            else
                return it.hasNext();
        }
        return true;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        return it.next();
    }

    @Override
    public void rewind() {
        counter=0;
        it=table[counter].iterator();
    }
}
