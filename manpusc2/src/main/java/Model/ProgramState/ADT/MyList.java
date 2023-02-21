package Model.ProgramState.ADT;

import java.util.Iterator;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> myList;

    public MyList(List<T> myList) {
        this.myList = myList;
    }

    public List<T> getMyList() {
        return myList;
    }

    @Override
    public void add(T elem) {
        myList.add(elem);
    }

    @Override
    public void remove(int pos) {
        myList.remove(pos);
    }

    @Override
    public String toString() {
        return myList.toString();
    }

    @Override
    public T get(int pos) {
        return myList.get(pos);
    }

    @Override
    public Iterator<T> getIter() {
        synchronized (this) {
            return myList.listIterator();
        }
    }

    @Override
    public boolean isEmpty() {
        return myList.isEmpty();

    }
}
