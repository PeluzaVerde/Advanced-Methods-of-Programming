package Model.ProgramState.ADT;

import Model.Value.Value;
import java.util.*;

public class MyHeap<K,V> implements MyIHeap<K,V> {

     Map<K,V> myDict; //maybe just Map if errors;;;;
     Stack<Integer> freeAdr;
    Integer index;

    public MyHeap(HashMap<K,V> myDict)
    {
        this.myDict = myDict;
        this.freeAdr = new Stack<>();
        this.index = 1;
    }

    public Map<K,V> getmyHeap(){
        synchronized (this){
    return myDict;
        }
    }

    public int getCurAdr(){
        return index;
    }

    @Override
    public K add(V value) {
        synchronized (this) {
            K key = this.getFree();
            myDict.put(key,value);
            return key;
        }
    }


    @Override
    public void update(K key, V value) {
        synchronized (this) {
            myDict.put(key, value);
        }
    }

    @Override
    public V lookup(K key) {
        synchronized (this){
            return myDict.get(key);
        }
    }

    @Override
    public boolean isDefined(K key) {
        synchronized (this) {
            return myDict.get(key) != null;
        }
    }

    @Override
    public void remove(K key) {
        synchronized (this) {
            myDict.remove(key);
            freeAdr.push((Integer) key);
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return myDict.isEmpty();
        }
    }

    @Override
    public String toString(){
        synchronized (this){
            return myDict.toString();
        }
    }

    @Override
    public Set<Map.Entry<K,V>>getSet(){
        synchronized (this){
            return myDict.entrySet();
        }
    }

    private K getFree(){
        synchronized (this){
            return (K) (freeAdr.isEmpty() ? this.index++ : freeAdr.pop());
        }
    }


    @Override
    public  void setContent(Map<K,V>newMap){
        synchronized (this){
            myDict.clear();;
            for(K i: newMap.keySet()){
                myDict.put(i, newMap.get(i));
            }
        }
    }


    @Override
    public Map<K,V> getContent(){
        synchronized (this){
            return myDict;
        }
    }



   /* private HashMap<K,V> myDict;

    public myDict(HashMap<K, V> myDict) {
        this.myDict = myDict;
    }

    public HashMap<K, V> getmyDict() {
        return myDict;
    }

    @Override
    public void add(K key, V value) {
        myDict.put(key,value);
    }

    @Override
    public void update(K key, V value) {
        myDict.put(key,value);
    }

    @Override
    public V lookup(K key) {
        return myDict.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return myDict.get(key) != null;
    }

    @Override
    public String toString() {
        return myDict.toString();
    }

    @Override
    public void remove(K key) {
        myDict.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return myDict.isEmpty();
    }

*/


//    private HashMap<Integer,Integer> myDict;
//    private int freeLocation;
//
//    public myDict(HashMap<Integer, Integer> myDict, int freeLocation) {
//        this.myDict = myDict;
//        this.freeLocation = freeLocation;
//    }
//
//    public HashMap<Integer, Integer> getmyDict() {
//        return myDict;
//    }
//
//    public int getFreeLocation() {
//        return freeLocation;
//    }
//
//    public void add(int key, int value) {
//        myDict.put(key,value);
//    }
//
//    public void update(int key, int value) {
//        myDict.put(key,value);
//    }
//
//    public int lookup(int key) {
//        return myDict.get(key);
//    }
//
//    public boolean isDefined(int key) {
//        return myDict.get(key) != null;
//    }

//    @Override
//    public void add(K key, V value) {
//
//    }
//
//    @Override
//    public void update(K key, V value) {
//
//    }
//
//    @Override
//    public V lookup(K key) {
//        return null;
//    }
//
//    @Override
//    public boolean isDefined(K key) {
//        return false;
//    }
//
//    @Override
//    public void remove(K key) {
//
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }

//    public String toString() {
//        return myDict.toString();
//    }
//
//    @Override
//    public void remove(K key) {
//
//    }
//
//    public void remove(int key) {
//        myDict.remove(key);
//    }
//
//    public boolean isEmpty() {
//        return myDict.isEmpty();
//    }

}
