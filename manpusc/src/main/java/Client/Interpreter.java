package Client;

import Controller.Controller;
import Model.Expression.*;
import Model.ProgramState.ADT.*;
import Model.ProgramState.PrgState;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repo.IRepo;
import Repo.Repo;
import View.Command.ExitCommand;
import View.Command.RunExample;
import View.TextMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Interpreter {
    public static void main(String[] args){



        Stmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIDict<String, Type> typeEnv1 = new MyDict<>(new HashMap<>());
        if(ex1.typecheck(typeEnv1).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr1 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex1,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()));
        ArrayList<PrgState> list1 = new ArrayList<>();
        list1.add(pr1);
        IRepo repo1 = new Repo(list1,"log1.txt");
        Controller ctr1 = new Controller(repo1);

        Stmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyIDict<String, Type> typeEnv2 = new MyDict<>(new HashMap<>());
        if(ex2.typecheck(typeEnv2).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr2 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex2,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()));
        ArrayList<PrgState> list2 = new ArrayList<>();
        list2.add(pr2);
        IRepo repo2 = new Repo(list2,"log2.txt");
        Controller ctr2 = new Controller(repo2);

        Stmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        MyIDict<String, Type> typeEnv3 = new MyDict<>(new HashMap<>());
        if(ex3.typecheck(typeEnv3).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr3 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex3,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()));
        ArrayList<PrgState> list3 = new ArrayList<>();
        list3.add(pr3);
        IRepo repo3 = new Repo(list3,"log3.txt");
        Controller ctr3 = new Controller(repo3);

        Stmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("Test.in"))),new CompStmt(new OpenFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc", new IntType()),new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
        MyIDict<String, Type> typeEnv4 = new MyDict<>(new HashMap<>());
        if(ex4.typecheck(typeEnv4).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr4 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex4,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()));
        ArrayList<PrgState> list4 = new ArrayList<>();
        list4.add(pr4);
        IRepo repo4 = new Repo(list4,"log4.txt");
        Controller ctr4 = new Controller(repo4);

        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocationStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocationStmt("a",new VarExp("v")),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocationStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocationStmt("a",new VarExp("v")),new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),new PrintStmt(new ArithExp(1,new HeapReadExp(new HeapReadExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocationStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))),new CompStmt(new HeapWriteStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new ArithExp(1,new HeapReadExp(new VarExp("v")),new ValueExp(new IntValue(5))))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocationStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocationStmt("a",new VarExp("v")),new CompStmt(new HeapAllocationStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));


        Stmt ex5 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationalExp(2,new VarExp("v"),new ValueExp(new IntValue(0))),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),new PrintStmt(new VarExp("v")))));
       MyIDict<String, Type> typeEnv5 = new MyDict<>(new HashMap<>());
        if(ex5.typecheck(typeEnv5).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
            PrgState pr5 = new PrgState(new MyStack<>(new Stack<>()), new MyDict<>(new HashMap<>()), new MyList<>(new ArrayList<>()), ex5, new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()));
            ArrayList<PrgState> list5 = new ArrayList<>();
            list5.add(pr5);
            IRepo repo5 = new Repo(list5, "log5.txt");
            Controller ctr5 = new Controller(repo5);


        Stmt ex6 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),new CompStmt(new HeapAllocationStmt("a",new ValueExp(new IntValue(22))),new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a",new ValueExp(new IntValue(30))),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadExp(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadExp(new VarExp("a")))))))));
        MyIDict<String, Type> typeEnv6 = new MyDict<>(new HashMap<>());
        if(ex6.typecheck(typeEnv6).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr6 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex6,new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()));
        ArrayList<PrgState> list6 = new ArrayList<>();
        list6.add(pr6);
        IRepo repo6 = new Repo(list6,"log6.txt");
        Controller ctr6 = new Controller(repo6);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1", ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(),ctr6));
        menu.show();
    }
}