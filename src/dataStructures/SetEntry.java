package dataStructures;

import java.io.Serializable;

/**
 * Includes methods of an Entry that are not supposed to be available to Main
 * @author Alexandre Peres 61615
 * @author Tomás Ferreira 61733
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public interface SetEntry<K,V> extends Entry<K,V> {

    /**
     * Sets a new value for the entry
     * @param newValue - new value of the entry
     */
    void setValue(V newValue);

    /**
     * Sets a new key for the entry
     * @param newKey - new key of the entry
     */
    void setKey(K newKey);
}
