package Model.Statement;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.ADT.MyIList;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements Stmt{
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> list = state.getOut();
        MyIDict<String,Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();
        list.add(exp.eval(tbl,heap));
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print("+exp.toString()+")";
    }

    @Override
    public Stmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}
