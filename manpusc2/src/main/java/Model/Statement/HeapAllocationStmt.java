package Model.Statement;
import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.MyException;

import java.sql.Ref;

public class HeapAllocationStmt implements Stmt{

    String var_name;
    Exp exp;
    public HeapAllocationStmt(String var_name, Exp exp){
        this.var_name=var_name;
        this.exp = exp;
    }



    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String,Value> tbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();

        if(!tbl.isDefined(var_name))
            throw new MyException(""+var_name +"undeclared");
        if(!(tbl.lookup(var_name).getType() instanceof RefType))
            throw new MyException(""+var_name +"incorrect type");

        Value value = exp.eval(tbl,heap);
        RefValue var_value = (RefValue) tbl.lookup(var_name);

        if(!var_value.getLocationType().equals(value.getType()))
            throw new MyException(""+var_name +" location type different from expression");
        int key = heap.add(value);
        tbl.update(var_name, new RefValue(key, value.getType()));
        return null;


    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = exp.typecheck(typeEnv);
        if(typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("HeapAllocationStmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString(){
        return "new("+var_name+","+exp.toString()+")";
    }

    @Override
    public Stmt deepCopy() {
        return new HeapAllocationStmt(var_name, exp);
    }
}
