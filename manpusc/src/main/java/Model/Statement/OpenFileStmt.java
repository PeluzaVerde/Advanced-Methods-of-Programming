package Model.Statement;

import Model.Expression.Exp;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIHeap;
import Model.ProgramState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;
import Exception.MyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStmt implements Stmt {

    Exp exp;

    public OpenFileStmt(Exp exp) {this.exp = exp;}


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<StringValue, BufferedReader> ftbl = state.getFileTable();
        MyIDict<String,Value> stbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMHeap();
        Value val = exp.eval(stbl,heap);
        if(!val.getType().equals(new StringType()))
            throw new MyException("Expression "+exp.toString()+" is not of type string");
        StringValue sval = (StringValue) val;
        if(ftbl.lookup(sval) != null)
            throw new MyException("Filepath " + sval + " already exists in file table");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sval.getVal()));
            ftbl.add(sval,reader);
        } catch (FileNotFoundException e) {
            throw new MyException("Error executing openFile operation");
        }
        return null;

    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
if(!exp.typecheck(typeEnv).equals(new StringType()))
            throw new MyException("OpenFileStmt: Expression "+exp.toString()+" is not of type string");
        return  typeEnv;
    }

    @Override
    public Stmt deepCopy() {
        return new OpenFileStmt(exp);
    }

    @Override
    public String toString() {
        return "openFile("+exp.toString()+')';
    }
}

