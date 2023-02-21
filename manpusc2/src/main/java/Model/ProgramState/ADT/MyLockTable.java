package Model.ProgramState.ADT;
import Exception.MyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyLockTable<A,C> implements MyILockTable<A,C>{
    private HashMap<A,C> locktable;
    private int add=1;

    public MyLockTable(HashMap<A, C> locktable) {
        this.locktable = locktable;
    }

    @Override
    public HashMap<A, C> getLock() {
        synchronized (this){
            return locktable;
        }
    }

    @Override
    public void put(A int1, C int2){
        synchronized (this){
            locktable.put(int1,int2);
            add+=1;
        }
    }

    @Override
    public int getCurrentAddress() {
        return add;
    }

    @Override
    public C getContent(A int1) {
        synchronized (this){
            return locktable.get(int1);
        }
    }

    @Override
    public void update(A int1, C int2) {
        synchronized (this){
            if(locktable.containsKey(int1))
                locktable.replace(int1,int2);
        }
    }

    @Override
    public boolean contains(A int1) {
        synchronized (this){
            return locktable.containsKey(int1);
        }
    }

    @Override
    public void setLock(HashMap<A, C> lock) {
        synchronized (this){
            locktable=lock;
        }
    }

    @Override
    public Map<A, C> getContents() {
        synchronized (this){
            return locktable;
        }
    }

    @Override
    public Set<Map.Entry<A, C>> getSet() {
        synchronized (this){
            return (Set<Map.Entry<A, C>>) locktable.keySet();
        }
    }
    @Override
    public String toString() {
        synchronized (this){
            StringBuilder text = new StringBuilder();
            for(A key: locktable.keySet())
                text.append(key).append(" : ").append(locktable.get(key)).append("\n");
            return text.toString();
        }
    }
}