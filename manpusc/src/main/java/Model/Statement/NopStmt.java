package Model.Statement;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.Type;

public class NopStmt implements Stmt{
    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public Stmt deepCopy() {
        return new NopStmt();
    }
}
