package Model.ProgramState.ADT;

import Model.Statement.Stmt;

public interface MyIStack<T> {
    void push(T elem);
    T pop();
    boolean isEmpty();
    String toString();

    T peek();
}
