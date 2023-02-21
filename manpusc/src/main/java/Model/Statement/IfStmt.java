package Model.Statement;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStmt implements Stmt{
    Exp exp;
    Stmt thenStmt;
    Stmt elseStmt;

    public IfStmt(Exp exp, Stmt thenStmt, Stmt elseStmt) {
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Stmt> stk=state.getExeStack();
        MyIDict<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();
        Value cond=exp.eval(tbl,heap);
        if(cond.getType().equals(new BoolType()))
        {
            BoolValue boolCond= (BoolValue) cond;
            if(boolCond.getVal())
                stk.push(thenStmt);
            else
                stk.push(elseStmt);
        }
        else
            throw new MyException("Conditional expression is not a boolean");

        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            thenStmt.typecheck(typeEnv.deepCopy());
            elseStmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public String toString() {
        return "if("+exp.toString()+") then "+thenStmt.toString()+" else "+elseStmt.toString();
    }

    @Override
    public Stmt deepCopy() {
        return new IfStmt(exp.deepCopy(),thenStmt.deepCopy(),elseStmt.deepCopy());
    }


}
