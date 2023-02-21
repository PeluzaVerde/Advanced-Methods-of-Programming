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

public class UnlockStmt implements Stmt{
    private final String var;
    private static final Lock lock = new ReentrantLock();

    public UnlockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        if(state.getSymTable().isDefined(var)){
            if(state.getSymTable().lookup(var).getType().equals(new IntType()))
            {
                IntValue i = (IntValue) state.getSymTable().lookup(var);
                int found = i.getVal();
                if(state.getLockTable().contains(found))
                    if(state.getLockTable().getContent(found)== state.getId())
                        state.getLockTable().update(found,-1);

            }}
        else
            throw new MyException("Var not in symtable\n");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
       // if (typeEnv.lookup(var).equals(new IntType()))
            return typeEnv;
       // else
         //   throw new MyException("Var is not of type int!");
    }

    @Override
    public Stmt deepCopy() {
        return new UnlockStmt(var);
    }

    @Override
    public String toString() {
        return "unlock(" + var + ")";
    }
}
