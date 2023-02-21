package Model.Statement;

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

public class HeapWriteStmt implements  Stmt{
    Exp exp;
    String var_name;

    public HeapWriteStmt(String var_name, Exp exp) {
        this.var_name=var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String,Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();
        if(!tbl.isDefined(var_name))
            throw new MyException("Variable"+var_name+"isnt declared");
        if(!(tbl.lookup(var_name).getType() instanceof RefType))
            throw new MyException("Var"+var_name+" isnt refType");
        RefValue rv = (RefValue) tbl.lookup(var_name);
        if(!heap.isDefined(rv.getAddr()))
            throw new MyException("addres assoc with"+var_name+"isnt in heap");

        Value val = exp.eval(tbl,heap);
        if(!rv.getLocationType().equals(val.getType()))
            throw new MyException(""+var_name+" Has a diffent location type");
        heap.update(rv.getAddr(),val);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(typeEnv.lookup(var_name).equals(new RefType(exp.typecheck(typeEnv))))
            return typeEnv;
        else
            throw new MyException("HeapWriteStmt: right hand side and left hand side have different types ");


//        if(typeEnv.isDefined(var_name)){
//            Type typevar = typeEnv.lookup(var_name);
//            Type typexp = exp.typecheck(typeEnv);
//            if(typevar.equals(new RefType(typexp)))
//                return typeEnv;
//            else
//                throw new MyException("HeapWriteStmt: right hand side and left hand side have different types ");
//        }
//        else
//            throw new MyException("HeapWriteStmt: var_name is not defined in typeEnv");
//
    }

    @Override
    public String toString() {
        return "wH("+var_name+","+exp.toString()+")";
    }

    @Override
    public Stmt deepCopy() {
        return new HeapWriteStmt(var_name,exp);
    }
}
