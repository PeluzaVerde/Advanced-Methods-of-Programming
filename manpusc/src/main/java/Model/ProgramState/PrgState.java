package Model.ProgramState;

import Model.ProgramState.ADT.*;
import Model.Statement.Stmt;
import Model.Value.StringValue;
import Model.Value.Value;
import Exception.MyException;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class PrgState {
    MyIStack<Stmt> exeStack;
    MyIDict<String, Value> symTable;
    MyIList<Value> out;

    Stmt originalProgram;

    MyIDict<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer,Value> heap;

    int thread_id;
    static int id=0;


    public PrgState(MyIStack<Stmt> exeStack, MyIDict<String, Value> symTable, MyIList<Value> out, Stmt program,MyIDict<StringValue, BufferedReader> fileTable, MyIHeap<Integer, Value> heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable=fileTable;
        originalProgram = program.deepCopy();
        exeStack.push(program);
        this.heap=heap;
        setId();

    }


    synchronized private void setId(){
        thread_id=id;
        id++;
    }
    public MyIStack<Stmt> getExeStack() {
        return exeStack;
    }

    public MyIDict<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDict<StringValue,BufferedReader>getFileTable(){
        return fileTable;
    }

    public  MyIHeap<Integer,Value>getMHeap(){return  heap;}
    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder("Thread "+thread_id+"\nExecution stack:\n");
        String initialString;
        String[] strings;

        Stack<Stmt> helper = new Stack<>();
        while(!exeStack.isEmpty()) {
            Stmt crtStmt = exeStack.pop();
            myString.append(crtStmt.toString()).append('\n');
            helper.push(crtStmt);
        }
        while(!helper.isEmpty())
        {
            Stmt crtStmt = helper.pop();
            exeStack.push(crtStmt);
        }

        myString.append("SymTable:\n");
        if(!symTable.isEmpty()) {
            for(Map.Entry<String,Value> r : symTable.getSet())
            {
                myString.append(r.toString().replace("=","->")).append('\n');
            }
        }

        myString.append("Out:\n");
        if(!out.isEmpty()) {
            Iterator<Value> i = out.getIter();
            while (i.hasNext())
            {
                myString.append(i.next().toString()).append('\n');
            }
        }

        myString.append("FileTable:\n");
        if(!fileTable.isEmpty()) {
            for(Map.Entry<StringValue,BufferedReader> r : fileTable.getSet())
            {
                myString.append(r.toString().replace("=","->")).append('\n');
            }
        }

        myString.append("Memory heap:\n");
        if(!heap.isEmpty()) {
            for(Map.Entry<Integer,Value> r : heap.getSet())
            {
                myString.append(r.toString().replace("=","->")).append('\n');
            }
        }

        return myString.toString();
        ///return "Execution stack: "+exeStack.toString()+"\n"+"Symbols table: "+symTable.toString()+"\n"+"Output: "+out.toString();
    }

    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(!exeStack.isEmpty())
        {
            Stmt crtStmt=exeStack.pop();
            return crtStmt.execute(this);
        }
        else
            throw new MyException("Execution stack is empty");
    }

    public int getId() {
        return thread_id;
    }

    public MyIList<Stmt> getStackAsList() {
        MyIList<Stmt> list = new MyList<>(new ArrayList<>());
        Stack<Stmt> helper = new Stack<>();
        while(!exeStack.isEmpty()) {
            Stmt crtStmt = exeStack.pop();
            list.add(crtStmt);
            helper.push(crtStmt);
        }
        while(!helper.isEmpty())
        {
            Stmt crtStmt = helper.pop();
            exeStack.push(crtStmt);
        }
        return list;
    }
}
