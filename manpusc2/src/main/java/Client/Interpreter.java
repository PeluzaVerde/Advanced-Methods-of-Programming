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
        PrgState pr1 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex1,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
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
        PrgState pr2 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex2,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
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
        PrgState pr3 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex3,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
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
        PrgState pr4 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex4,new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
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
            PrgState pr5 = new PrgState(new MyStack<>(new Stack<>()), new MyDict<>(new HashMap<>()), new MyList<>(new ArrayList<>()), ex5, new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
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
        PrgState pr6 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex6,new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
        ArrayList<PrgState> list6 = new ArrayList<>();
        list6.add(pr6);
        IRepo repo6 = new Repo(list6,"log6.txt");
        Controller ctr6 = new Controller(repo6);

//        Stmt ex9 = new CompStmt(new VarDeclStmt("v1", new IntType()), new CompStmt(new VarDeclStmt("v2", new IntType()), new CompStmt(new AssignStmt("v1", new ValueExp(new IntValue(20))),
//                new CompStmt(new AssignStmt("v2", new ValueExp(new IntValue(30))), new CompStmt(new NewLockStmt("x"), new CompStmt(new ForkStmt( new CompStmt( new ForkStmt(
//                        new CompStmt(new LockStmt("x"),new CompStmt( new HeapWriteStmt("v1", new ArithExp('-', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1)))), new UnlockStmt("x")))),
//                        new CompStmt(new LockStmt("x"),new CompStmt( new HeapWriteStmt("v1", new ArithExp('+', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1)))), new UnlockStmt("x"))))),
//                        new CompStmt( new ForkStmt( new CompStmt( new ForkStmt( new HeapWriteStmt( "v2", new ArithExp( '+', new HeapReadExp( new VarExp( "v2")), new ValueExp( new IntValue( 1))))),
//                                new CompStmt(new HeapWriteStmt("v2", new ArithExp('+', new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(1)))), new UnlockStmt("x")))),
//                                new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),
//                                        new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),new CompStmt(new NopStmt(),
//                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))),new PrintStmt(new HeapReadExp(new VarExp("v2"))))))))))))))))))));
//
//        MyIDict<String, Type> typeEnv9 = new MyDict<>(new HashMap<>());
//        if(ex9.typecheck(typeEnv9).equals(new BoolValue(false)))
//        {
//            System.out.println("Typecheck error");
//            return;
//        }
//        PrgState pr9 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex9,new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyLockTable<>(new HashMap<>()));
//        ArrayList<PrgState> list9 = new ArrayList<>();
//        list9.add(pr9);
//        IRepo repo9 = new Repo(list9,"log9.txt");
//        Controller ctr9 = new Controller(repo9);


        Stmt ex11 = new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),new CompStmt(new HeapAllocationStmt("v1",new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),
                        new CompStmt(new HeapAllocationStmt("v2",new ValueExp(new IntValue(30))),
                                new CompStmt(new NewLockStmt("x"),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new ForkStmt(
                                                        new CompStmt(new LockStmt("x"),
                                                                new CompStmt(new HeapWriteStmt("v1", new ArithExp('-',new HeapReadExp(new VarExp("v1")),new ValueExp(new IntValue(1)))),
                                                                        new CompStmt(new UnlockStmt("x"),new NopStmt())))),
                                                        new CompStmt(new LockStmt("x"),
                                                                new CompStmt(new HeapWriteStmt("v1", new ArithExp('+',new HeapReadExp(new VarExp("v1")),new ValueExp(new IntValue(1)))),
                                                                        new CompStmt(new UnlockStmt("x"),new NopStmt()))))),
                                                new CompStmt(new ForkStmt(
                                                        new CompStmt(new ForkStmt(new HeapWriteStmt("v2", new ArithExp('+',new HeapReadExp(new VarExp("v2")),new ValueExp(new IntValue(1))))),
                                                                new CompStmt(new HeapWriteStmt("v2", new ArithExp('+',new HeapReadExp(new VarExp("v2")),new ValueExp(new IntValue(1)))),
                                                                        new CompStmt(new UnlockStmt("x"),new NopStmt())))),
                                                        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))),
                                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v2"))),new NopStmt())))))))));


        MyIDict<String, Type> typeEnv11 = new MyDict<>(new HashMap<>());
//        if(ex11.typecheck(typeEnv11).equals(new BoolValue(false)))
//        {
//            System.out.println("Typecheck error");
//            return;
//        }
        PrgState pr11 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex11,new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyLockTable<>(new HashMap<>()));
        ArrayList<PrgState> list11 = new ArrayList<>();
        list11.add(pr11);
        IRepo repo11 = new Repo(list11,"log11.txt");
        Controller ctr11 = new Controller(repo11);


//        MyIStack<Stmt> exeStk11 = new MyStack<Stmt>(new Stack<>());
//        MyIDictionary<String, Value> symTbl11 = new MyDict<>(new HashMap<>());
//        MyIList<Value> out11 = new MyList<>(new ArrayList<>());
//        MyIDictionary<StringValue, BufferedReader> fileTable11 = new MyDictionary<>(new HashMap<>());
//        IHeap<Integer,Value> heap11 = new Heap<>(new HashMap<>());



//        try{
//            ex11.typecheck(typeEnv);
//        } catch (MyException e) {
//            throw new RuntimeException(e);
//        }
//        ILock<Integer,Integer> locktable11 = new Lock<>(new HashMap<>());
//        PrgState prg11= new PrgState(symTbl11, exeStk11, out11, ex11, fileTable11,heap11,1,locktable11);
//        IRepo repo11 = new Repo(prg11, "log11.txt");
//        Controller ctr11 = new Controller(repo11);

/*
        Stmt ex7 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("x", new IntType()),
                                new CompStmt(new VarDeclStmt("q", new IntType()),
                                        new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(20))),
                                                new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new NewLockStmt("x"),
                                                                new CompStmt(new ForkStmt(
                                                                        new CompStmt(new ForkStmt(
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new HeapWriteStmt("v1", new ArithExp('-', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1)))),
                                                                                                new UnlockStmt("x")))
                                                                        ),
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new HeapWriteStmt("v1", new ArithExp('*', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                                                                                new UnlockStmt("x"))))
                                                                ),
                                                                        new CompStmt( new NewLockStmt("q"),
                                                                                new CompStmt(new ForkStmt(
                                                                                        new CompStmt( new ForkStmt(
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new HeapWriteStmt("v2", new ArithExp('+', new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(5)))),
                                                                                                                new UnlockStmt("q")))
                                                                                        ),
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new HeapWriteStmt("v2", new ArithExp('*', new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                                                                                new UnlockStmt("q"))))
                                                                                ),
                                                                                        new CompStmt(new NopStmt(),
                                                                                                new CompStmt(new NopStmt(),
                                                                                                        new CompStmt(new NopStmt(),
                                                                                                                new CompStmt(new NopStmt(),
                                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))),
                                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                                        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v2"))),
                                                                                                                                                                new UnlockStmt("q"))))))))))))))))))));






        MyIDict<String, Type> typeEnv7 = new MyDict<>(new HashMap<>());
        if(ex7.typecheck(typeEnv7).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr7 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex7,new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyLockTable<>(new HashMap<>()));
        ArrayList<PrgState> list7 = new ArrayList<>();
        list7.add(pr7);
        IRepo repo7 = new Repo(list7,"log7.txt");
        Controller ctr7 = new Controller(repo7);












        Stmt ex8 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("x", new IntType()),
                                new CompStmt(new VarDeclStmt("q", new IntType()),
                                        new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(20))),
                                                new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new NewLockStmt("x"),
                                                                new CompStmt(new ForkStmt(
                                                                        new CompStmt(new ForkStmt(
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new HeapWriteStmt("v1", new ArithExp('-', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1)))),
                                                                                                new UnlockStmt("x")))
                                                                        ),
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new HeapWriteStmt("v1", new ArithExp('*', new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                                                                                new UnlockStmt("x"))))
                                                                ),
                                                                        new CompStmt( new NewLockStmt("q"),
                                                                                new CompStmt(new ForkStmt(
                                                                                        new CompStmt( new ForkStmt(
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new HeapWriteStmt("v2", new ArithExp('+', new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(5)))),
                                                                                                                new UnlockStmt("q")))
                                                                                        ),
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new HeapWriteStmt("v2", new ArithExp('*', new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                                                                                                                new UnlockStmt("q"))))
                                                                                ),
                                                                                        new CompStmt(new NopStmt(),
                                                                                                new CompStmt(new NopStmt(),
                                                                                                        new CompStmt(new NopStmt(),
                                                                                                                new CompStmt(new NopStmt(),
                                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                                new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))),
                                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                                        new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v2"))),
                                                                                                                                                                new UnlockStmt("q"))))))))))))))))))));

        MyIDict<String, Type> typeEnv8 = new MyDict<>(new HashMap<>());
        if(ex8.typecheck(typeEnv8).equals(new BoolValue(false)))
        {
            System.out.println("Typecheck error");
            return;
        }
        PrgState pr8 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),ex8,new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()), new MyLockTable<>(new HashMap<>()));
        ArrayList<PrgState> list8 = new ArrayList<>();
        list8.add(pr8);
        IRepo repo8 = new Repo(list8,"log8.txt");
        Controller ctr8 = new Controller(repo8);

*/

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1", ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(),ctr6));
        //menu.addCommand(new RunExample("7", ex7.toString(),ctr7));

//        menu.addCommand(new RunExample("8", ex8.toString(),ctr8));
        //menu.addCommand(new RunExample("9", ex9.toString(),ctr9));
        menu.addCommand(new RunExample("11", ex11.toString(),ctr11));
        menu.show();
    }
}