package Model.Statement;

import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.ProgramState.ADT.MyILockTable;
import Model.Value.IntValue;
import Model.Value.Value;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.ADT.MyIList;
import Model.ProgramState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.MyException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLockStmt implements Stmt{
    private String var;
    private Lock lock = new ReentrantLock();

    public NewLockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyILockTable<Integer,Integer> lockTable = state.getLockTable();
        int freeAddress = lockTable.getCurrentAddress();
        lockTable.put(freeAddress, -1);
        if (state.getSymTable().isDefined(var))
            state.getSymTable().update(var, new IntValue(freeAddress));
        else
            state.getSymTable().add(var, new IntValue(freeAddress));
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        //if (typeEnv.lookup(var).equals(new IntType()))
            return typeEnv;
        //else
            //throw new MyException("Var is not of int type!");
    }

    @Override
    public Stmt deepCopy() {
        return new NewLockStmt(var);
    }

    @Override
    public String toString() {
        return "newLock(" + var + ")";
    }
}
