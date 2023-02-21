package Model.Statement;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.Type;

public interface Stmt {
    PrgState execute(PrgState state) throws MyException;

    MyIDict<String, Type> typecheck(MyIDict<String,Type> typeEnv) throws MyException;

    Stmt deepCopy();
}
