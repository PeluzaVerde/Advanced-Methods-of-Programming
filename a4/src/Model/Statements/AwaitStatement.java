package Model.Statements;

import Model.Exceptions.*;
import Model.Expressions.IExpression;
import Model.State.PrgState;
import Model.Types.IntType;
import Model.Types.IType;
import Model.DataStructures.IDictionary;
import Model.DataStructures.IHeap;
import Model.DataStructures.MyILatchTable;
import Model.Values.IntValue;
import Model.Values.IValue;


import java.lang.reflect.Type;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class AwaitStatement implements IStatement{
    private final String var;
    private static final Lock lock = new ReentrantLock();

    public AwaitStatement(String var) {
        this.var = var;
    }
//    @Override
//    public PrgState execute(PrgState state) throws EvaluationException {
//        lock.lock();
//        IDictionary<String, IValue> symTable = state.getSymTable();
//        MyILatchTable latchTable = state.getLatchTable();
//        if (symTable.isDefined(var)) {
//            IntValue fi = (IntValue) symTable.lookUp(var);
//            int foundIndex = fi.getVal();
//            if (latchTable.containsKey(foundIndex)) {
//                if (latchTable.get(foundIndex) != 0) {
//                    state.getExeStack().push(this);
//                }
//            } else {
//                throw new EvaluationException("Index not found in the latch table!");
//            }
//        } else {
//            throw new EvaluationException("Variable not defined!");
//        }
//        lock.unlock();
//        return null;
//    }


    @Override
    public PrgState execute(PrgState state) throws EvaluationException {
        lock.lock();
        IDictionary<String, IValue> symTable = state.getSymTable();
        MyILatchTable latchTable = state.getLatchTable();
        if (symTable.isDefined(var)) {
            if(symTable.lookUp(var).getType().equals(new IntType())) {
                IntValue fi = (IntValue) symTable.lookUp(var);
                int foundIndex = fi.getVal();
                if (latchTable.containsKey(foundIndex)) {
                    if (latchTable.get(foundIndex) != 0) {
                        state.getExeStack().push(this);
                    }
                } else {
                    throw new EvaluationException("Index not found in the latch table!");
                }
            }
            else{
                throw new EvaluationException("Variable is not an integer!");
            }
        } else {
            throw new EvaluationException("Variable not defined!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws EvaluationException {
        if (typeEnv.lookUp(var).equals(new IntType()))
            return typeEnv;
        else
            throw new EvaluationException(String.format("%s is not of int type!", var));
    }

//    @Override
//    public IStatement deepCopy() {
//        return new AwaitStatement(var);
//    }

    @Override
    public String toString() {
        return String.format("await(%s)", var);
    }
}