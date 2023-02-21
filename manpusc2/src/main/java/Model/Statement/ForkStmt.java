package Model.Statement;

import Controller.Controller;
import Model.ProgramState.ADT.*;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.Type;
import Repo.IRepo;
import Repo.Repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ForkStmt implements Stmt{
    Stmt stmt;

    public ForkStmt(Stmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        PrgState pr = new PrgState(new MyStack<>(new Stack<>()), state.getSymTable().deepCopy(), state.getOut(),stmt,state.getFileTable(), state.getMHeap(), state.getLockTable());
        return pr;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public Stmt deepCopy() {
        return new ForkStmt(stmt);
    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";
    }
}
