package Model.Statement;

import Model.ProgramState.ADT.MyDict;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.Type;

public class CompStmt implements Stmt{
    Stmt first;
    Stmt second;

    public CompStmt(Stmt first, Stmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Stmt> stk=state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }


    @Override
    public String toString() {
        return "("+first.toString()+";"+second.toString()+")";
    }
    @Override
    public Stmt deepCopy() {
        return new CompStmt(first.deepCopy(),second.deepCopy());
    }

}
