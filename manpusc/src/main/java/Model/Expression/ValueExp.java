package Model.Expression;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public class ValueExp implements Exp{
    Value val;

    public ValueExp(Value val) {
        this.val = val;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException {
        return val;
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return val.getType();
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(val.deepCopy());
    }
}
