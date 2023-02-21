package Model.Statement;

import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Exception.MyException;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarDeclStmt implements Stmt{
    String name;
    Type type;

    public VarDeclStmt(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl = state.getSymTable();
        if(!tbl.isDefined(name))
        {
            tbl.add(name,type.defaultValue());
        }
        else
            throw new MyException("Variable is already declared");

        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        typeEnv.add(name,type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString()+" "+name;
    }

    @Override
    public Stmt deepCopy() {
        return new VarDeclStmt(name,type.deepCopy());
    }
}
