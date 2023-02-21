package Model.ProgramState.ADT;

import java.util.Iterator;

public interface MyIList<T> {
    void add(T elem);
    void remove(int pos);
    String toString();
    T get(int pos);

    Iterator<T> getIter();

    boolean isEmpty();
}
