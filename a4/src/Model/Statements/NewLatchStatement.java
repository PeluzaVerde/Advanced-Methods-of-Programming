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

public class NewLatchStatement implements IStatement{
    private final String var;
    private final IExpression expression;
    private static final Lock lock = new ReentrantLock();

    public NewLatchStatement(String var, IExpression expression) {
        this.var = var;
        this.expression = expression;
    }

//    @Override
//    public PrgState execute(PrgState state) throws EvaluationException {
//        lock.lock();
//        IDictionary<String, IValue> symTable = state.getSymTable();
//        IHeap heap = state.getHeap();
//        MyILatchTable latchTable = state.getLatchTable();
//        IntValue nr = (IntValue) (expression.eval(symTable, heap));
//        int number = nr.getVal();
//        int freeAddress = latchTable.getFreeAddress();
//        latchTable.put(freeAddress, number);
//        if (symTable.isDefined(var)) {
//            symTable.update(var, new IntValue(freeAddress));
//        } else {
//            throw new EvaluationException(String.format("%s is not defined in the symbol table!", var));
//        }
//        lock.unlock();
//        return null;
//    }
@Override
public PrgState execute(PrgState state) throws EvaluationException {
    lock.lock();
    IDictionary<String, IValue> symTable = state.getSymTable();
    IHeap heap = state.getHeap();
    MyILatchTable latchTable = state.getLatchTable();
    IntValue num1 = (IntValue) (expression.eval(symTable, heap));
    if(num1 == null)
        throw new EvaluationException("Expression is not an integer!");
    int number = num1.getVal();
    int freeAddress = latchTable.getFreeAddress();
    latchTable.put(freeAddress, number);
    if (symTable.isDefined(var) && symTable.lookUp(var).getType().equals(new IntType())) {
        symTable.update(var, new IntValue(freeAddress));
    } else {
        throw new EvaluationException(String.format("%s is not defined in the symbol table!", var));
    }
    lock.unlock();
    return null;
}
    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws EvaluationException {
        if (typeEnv.lookUp(var).equals(new IntType())) {
            if (expression.typeCheck(typeEnv).equals(new IntType())) {
                return typeEnv;
            } else {
                throw new EvaluationException("Expression doesn't have the int type!");
            }
        } else {
            throw new EvaluationException(String.format("%s is not of int type!", var));
        }
    }

//    @Override
//    public IStatement deepCopy() {
//        return new NewLatchStatement(var, expression.deepCopy());
//    }

    @Override
    public String toString() {
        return String.format("newLatch(%s, %s)", var, expression);
    }
}