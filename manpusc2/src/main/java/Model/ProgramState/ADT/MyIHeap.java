package Model.ProgramState.ADT;
import java.util.*;

public interface MyIHeap<K,V> {
    K add( V value);
    void update(K key, V value);
    V lookup(K key);
    boolean isDefined(K key);
    String toString();
    void remove(K key);
    boolean isEmpty();
    Set<Map.Entry<K,V>> getSet();
    void setContent(Map<K,V> newMap);
    Map<K,V> getContent();

}
