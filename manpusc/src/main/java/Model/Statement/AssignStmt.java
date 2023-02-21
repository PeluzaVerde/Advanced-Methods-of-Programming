package Model.Statement;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIFileTable;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class AssignStmt implements Stmt{
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();

        if (tbl.isDefined(id))
        {
            Value val=exp.eval(tbl,heap);
            Type typeId=tbl.lookup(id).getType();
            if (val.getType().equals(typeId))
                tbl.update(id, val);
            else
                throw new MyException("Declared type of variable"+id+" and type of the assigned expression do not match");
        }
        else
            throw new MyException("The used variable " +id + " was not declared before");
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typevar=typeEnv.lookup(id);
        Type typexp=exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return id+"="+exp.toString();
    }

    @Override
    public Stmt deepCopy() {
        return new AssignStmt(id,exp);
    }

}
