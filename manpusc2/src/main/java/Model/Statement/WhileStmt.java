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


public class WhileStmt implements Stmt{
    Exp exp;
    Stmt stmt;

public WhileStmt(Exp exp, Stmt stmt){
    this.exp = exp;
    this.stmt=stmt;
}


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Stmt> stk = state.getExeStack();
        MyIDict<String,Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();

        Value val = exp.eval(tbl,heap);
        if(!(val instanceof BoolValue))
            throw new MyException("Exp"+exp.toString()+" isnt boolean");
        if(((BoolValue)val).getVal()){
            stk.push(new WhileStmt(exp,stmt));
            stk.push(stmt);
        }
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if(typexp.equals(new BoolType()))
        {
            stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of While has not the type bool");
    }

    @Override
    public Stmt deepCopy() {
        return null;
    }

    @Override
    public String toString(){
    return "WhileStmt{"+"exp="+ exp + "}";

    }
}
