package Controller;

import Model.ProgramState.ADT.MyDict;
import Model.ProgramState.ADT.MyIDict;
import Model.ProgramState.ADT.MyIList;
import Model.ProgramState.ADT.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statement.Stmt;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Repo.IRepo;
import Exception.MyException;
import java.io.IOException;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class Controller {
    IRepo repo;

    ExecutorService executor;

    List<PrgState> prgList;
    public Controller(IRepo repo) {
        this.repo = repo;
        for (PrgState state: repo.getPrgList()) {
            MyIDict<String, Type> typeEnv = new MyDict<>(new HashMap<>());
            state.getExeStack().peek().typecheck(typeEnv);
        }

        executor = Executors.newFixedThreadPool(2);
        prgList = removeCompletedPrg(repo.getPrgList());
    }

    public void addPrg(PrgState prg)
    {
        repo.addPrgState(prg);
    }


//    Map<Integer, Value> garbageCollector(List<Integer> symTableAddr,List<Integer> heapAddr, Map<Integer,Value> heap){
//        return heap.entrySet().stream()
//                .filter(e->symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey()))
//                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
//    }


    /*

    List<Integer> getAddrFromSymTable(List<Collection<Value>>SymTableValues, Map<Integer,Value> heap){
        List<Integer> toReturn = new ArrayList<>();
        SymTableValues.forEach(SymTable -> SymTable.stream()
                .filter(v->v instanceof RefValue)
                .forEach(v->{
                        while (v instanceof  RefValue){
                                toReturn.add(((RefValue)v).getAddr());
                            v = heap.get(((RefValue)v).getAddr());
                        }
                    }));

        return toReturn;
    }
*/
//    private List<Integer> getAddrFromHeap(Collection<Value> heapValues){
//        return heapValues.stream()
//                .filter(v->v instanceof RefValue)
//                .map(v->{
//                    RefValue v1 = (RefValue)v;
//                    return v1.getAddr();
//                })
//                .collect(Collectors.toList());
//    }


/*
    List<Integer> getAddrFromSymTable(Map<Integer,Value> heap, List<PrgState> prgList){
        List<Integer> toReturn = new ArrayList<>();
        for(PrgState p :prgList){
            p.getSymTable().getContent().values().stream()
                    .filter(v -> v instanceof RefValue)
                    .forEach(v -> {
                        while (v instanceof  RefValue){
                            if(!toReturn.contains(((RefValue)v).getAddr())){
                                toReturn.add(((RefValue)v).getAddr());
                            }
                            v = heap.get(((RefValue)v).getAddr());
                        }
                    });
        }
        return toReturn;
    }
*/

    /*
    PrgState oneStep(PrgState state) throws MyException{
        MyIStack<Stmt> stk= state.getExeStack();
        if(!stk.isEmpty())
        {
            Stmt crtStmt=stk.pop();
            return crtStmt.execute(state);
        }
        else
            throw new MyException("Execution stack is empty");
    }


     */
//    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
//        return symTableValues.stream()
//                .filter(v-> v instanceof RefValue)
//                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
//                .collect(Collectors.toList());}

  /*  public void allStep(){
//        PrgState prg=repo.getCrtPrg().get(0);
//        repo.logPrgStateExec();
//        //System.out.println(prg.toString());
//        while (!prg.getExeStack().isEmpty())
//        {
//            oneStep(prg);
//            repo.logPrgStateExec();
//            //(HashMap<Integer,Value>) v
//            prg.getMHeap().setContent( garbageCollector(getAddrFromSymTable(prg.getSymTable().getContent().values()),getAddrFromHeap(prg.getMHeap().getContent().values()),prg.getMHeap().getContent()));
//
//            //prg.getMHeap().setContent(garbageCollector( getAddrFromSymTable(prg.getSymTable().getContent().values(),prg.getMHeap().getContent()),prg.getMHeap().getContent()));
//
//
//            /*prg.getMHeap().setContent(garbageCollector(
//                    getAddrFromSymTable(prg.getSymTable().getContent().values(),prg.getMHeap().getContent())
//                    ,prg.getMHeap().getContent()));
//            */
    //repo.logPrgStateExec();
//            // System.out.println(prg.toString());
//        }
//    }


    Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Map<Integer,Value> heap, List<PrgState> prgList){
        List<Integer> toReturn = new ArrayList<>();
        for(PrgState p : prgList) {
            p.getSymTable().getContent().values().stream()
                    .filter(v -> v instanceof RefValue)
                    .forEach(v -> {
                        while (v instanceof RefValue) {
                            if(!toReturn.contains(((RefValue) v).getAddr())) {
                                toReturn.add(((RefValue) v).getAddr());
                            }
                            v = heap.get(((RefValue) v).getAddr());
                        }
                    });
        }
        return toReturn;
    }

    private void oneStepForAllPrg(List<PrgState> prgList) throws MyException{

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());
        List<P2> newPrgList = null;
        try{
            newPrgList = executor.invokeAll(callList).stream().map(future -> {
                try {
                    return new P2(future.get(),null);
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).filter(p -> p.first != null || p.second != null).collect(Collectors.toList());
        } catch (InterruptedException e){
            System.out.println("Could not invoke the callables");
        }
        assert newPrgList != null;
        prgList.addAll(newPrgList.stream().map(pair -> pair.first).collect(Collectors.toList()));
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        repo.setPrgList(prgList);
//        prgList.forEach(prg-> {
//            try {
//                repo.logPrgStateExec(prg);
//            } catch (IOException e) {
//                throw new MyException("Error creating file");
//            }
//        });
//
//        List<Callable<PrgState>> callList = prgList.stream()
//                .map((PrgState p)->(Callable<PrgState>)(()->{return p.oneStep();}))
//                .collect(Collectors.toList());
//        try {
//            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
//                    .map(future-> {
//                        try {
//                            return future.get();
//                        } catch (InterruptedException e) {
//                            throw new MyException("Error geting return value from future");
//                        } catch (ExecutionException e) {
//                            throw new MyException("Error geting return value from future");
//                        }
//                    })
//                    .filter(p->p!=null)
//                    .   collect(Collectors.toList());
//            prgList.addAll(newPrgList);
//        } catch (InterruptedException e) {
//            throw new MyException("Error getting stream of futures");
//        }
//
//        prgList.forEach(prg-> {
//            try {
//                repo.logPrgStateExec(prg);
//            } catch (IOException e) {
//                throw new MyException("Error creating file");
//            }
//        });
//
//        repo.setPrgList(prgList);
    }

    public void allStep2(){
        executor = Executors.newFixedThreadPool(10);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while(!prgList.isEmpty()){
            oneStepForAllPrg(prgList);
            prgList.get(0).getMHeap().setContent(garbageCollector(
                    getAddrFromSymTable(prgList.get(0).getMHeap().getContent(),prgList),
                    prgList.get(0).getMHeap().getContent()));
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();

        repo.getPrgList();
    }

    public void allStep(){
        if(!prgList.isEmpty()){
            oneStepForAllPrg(prgList);
            prgList.get(0).getMHeap().setContent(garbageCollector(
                    getAddrFromSymTable(prgList.get(0).getMHeap().getContent(),prgList),
                    prgList.get(0).getMHeap().getContent()));
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        else {
            executor.shutdownNow();
        }
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }


    public int getnrOfPrgs() {
        return repo.getPrgList().size();
    }

    public List<PrgState> getPrgList() {
        return repo.getPrgList();
    }
}
