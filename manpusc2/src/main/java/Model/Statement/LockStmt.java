package Model.Statement;


import Exception.MyException;
import Model.ProgramState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyILockTable;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStmt implements Stmt{
    private  String var;
    private Lock lock = new ReentrantLock();

    public LockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        if (state.getSymTable().isDefined(var))
            if (state.getSymTable().lookup(var).getType().equals(new IntType())) {

                IntValue fi = (IntValue) state.getSymTable().lookup(var);
                int foundIndex = fi.getVal();
                if (state.getLockTable().contains(foundIndex)) {
                    if(state.getLockTable().getContent(foundIndex) == -1)
                        state.getLockTable().update(foundIndex,state.getId());
                    else
                        state.getExeStack().push(this);
                }
                else
                    throw new MyException("Index not in locktable\n");
            }else throw new MyException("Var not of int type\n");
        else throw new MyException("Var not defined\n");
        lock.unlock();
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        //if (typeEnv.lookup(var).equals(new IntType())) {
            return typeEnv;
        //} else {
         //   throw new MyException("Var is not of int type!");
       // }
    }

    @Override
    public Stmt deepCopy() {
        return new LockStmt(var);
    }

    @Override
    public String toString()
    {
        return "lock(" + var + ")";
    }
   // public String toString() {
     //   return String.format("lock(%s)", var);
    //}


}