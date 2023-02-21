package Model.Expression;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.Value;
import Model.Value.RefValue;
import Exception.MyException;

import java.sql.Ref;


public class HeapReadExp implements Exp {

Exp exp;

    public HeapReadExp(Exp exp){
        this.exp=exp;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value val= exp.eval(tbl,heap);
        if(!(val instanceof RefValue rv))
            throw new MyException(""+exp+" isnt RefValeu");
        if(!heap.isDefined(rv.getAddr()))
            throw new MyException(""+rv.toString()+" value is undefined");

        return  heap.lookup(rv.getAddr());
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typ= exp.typecheck(typeEnv);
        if(typ instanceof RefType){
            RefType reft= (RefType) typ;
            return reft.getInner();
        }
        else
            throw new MyException("HeapReadExp: not a RefType");
    }

    @Override
    public Exp deepCopy() {
        return null;
    }

    @Override
    public String toString(){
        return "rH("+exp+")";
    }
}
