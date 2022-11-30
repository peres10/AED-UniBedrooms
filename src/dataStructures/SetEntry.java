package dataStructures;

import java.io.Serializable;

public interface SetEntry<K,V> extends Entry<K,V> {

    void setValue(V newValue);

    void setKey(K newKey);
}
