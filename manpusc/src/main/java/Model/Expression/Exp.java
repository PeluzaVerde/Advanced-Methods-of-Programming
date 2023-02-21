package Model.Expression;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Exception.MyException;

public interface Exp {
    Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException;
    Type typecheck(MyIDict<String,Type> typeEnv) throws MyException;
    Exp deepCopy();

}
