package Model.DataStructures;

import Model.Exceptions.*;

import java.util.HashMap;

public interface MyILatchTable {
    void put(int key, int value) throws EvaluationException;
    int get(int key) throws EvaluationException;
    boolean containsKey(int key);
    int getFreeAddress();
    void update(int key, int value) throws EvaluationException;
    void setFreeAddress(int freeAddress);
    HashMap<Integer, Integer> getLatchTable();
    void setLatchTable(HashMap<Integer, Integer> newLatchTable);
}