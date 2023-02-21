package Model.Statement;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIFileTable;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements Stmt {
    Exp exp;
    String var_name;

    public ReadFileStmt(Exp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> stbl = state.getSymTable();
        MyIDict<StringValue, BufferedReader> ftbl = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();

        Value varVal = stbl.lookup(var_name);
        if (varVal == null)
            throw new MyException("Variable " + var_name + " was not declared before");
        if (!varVal.getType().equals(new IntType()))
            throw new MyException("Variable " + var_name + " is not of type int");
        Value expVal = exp.eval(stbl,heap);
        if (!expVal.getType().equals(new StringType()))
            throw new MyException("Expression " + exp.toString() + " is not of type string");

        StringValue expStrVal = (StringValue) expVal;

        BufferedReader reader = ftbl.lookup(expStrVal);
        if (reader == null)
            throw new MyException("File path " + expStrVal.getVal() + " not in file table");

        try {
            int result;
            String line = reader.readLine();
            if (line == null) result = 0;
            else result = Integer.parseInt(line);
            stbl.update(var_name, new IntValue(result));
        } catch (IOException e) {
            throw new MyException("Error executing readFile operation");
        }

        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
Type varType = typeEnv.lookup(var_name);
        Type expType = exp.typecheck(typeEnv);
        if (!varType.equals(new IntType()))
            throw new MyException("Readfile err: Variable " + var_name + " is not of type int");
        if (!expType.equals(new StringType()))
            throw new MyException("Readfile err: Expression " + exp.toString() + " is not of type string");
        return typeEnv;
    }

    @Override
    public Stmt deepCopy() {
        return new ReadFileStmt(exp, var_name);
    }

    @Override
    public String toString() {
        return "readFile(" + exp.toString() + "," + var_name + ")";
    }
}