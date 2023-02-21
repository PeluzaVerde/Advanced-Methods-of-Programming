package Model.Statement;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIFileTable;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.PrgState;
import Exception.MyException;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.*;

public class CloseFileStmt implements Stmt{
    Exp exp;

    public CloseFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<StringValue, BufferedReader> ftbl = state.getFileTable();
        MyIDict<String,Value> stbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();

        Value val = exp.eval(stbl,heap);
        if(!val.getType().equals(new StringType()))
            throw new MyException("Expression "+exp.toString()+" is not of type string");
        StringValue strVal = (StringValue) val;
        BufferedReader reader = ftbl.lookup(strVal);
        if(reader == null)
            throw new MyException("File path " + strVal.getVal() + " not in file table");
        try {
            reader.close();
            ftbl.remove(strVal);
        } catch (IOException e) {
            throw new MyException("Error executing closeFile operation");
        }

        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!exp.typecheck(typeEnv).equals(new StringType()))
            throw new MyException("Expression "+exp.toString()+" is not of type string");
        return  typeEnv;
    }

    @Override
    public Stmt deepCopy() {
        return new CloseFileStmt(exp);
    }

    @Override
    public String toString() {
        return "closeFile("+exp.toString()+")";
    }
}
