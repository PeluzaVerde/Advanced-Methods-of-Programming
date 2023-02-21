package Repo;

import Model.ProgramState.PrgState;

import Exception.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Repo implements IRepo{
    List<PrgState> stateList;
    String logFilePath;

    public Repo(List<PrgState> stateList, String logFilePath) {
        this.stateList = stateList;
        this.logFilePath=logFilePath;
    }

//    @Override
//    public List<PrgState> getCrtPrg() {
//        return stateList;
//    }

    @Override
    public void addPrgState(PrgState state) {
        stateList.clear();
        stateList.add(state);
    }


    @Override
    public void logPrgStateExec(PrgState state) throws MyException,IOException{


//        try {
            PrintWriter logfile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            PrgState myPrg = state;
            logfile.print(myPrg.toString());
            logfile.close();
//        }
//        catch(Exception e) {
//            e.getStackTrace();
//        }


    }

    @Override
    public List<PrgState> getPrgList() {
        return stateList;
    }

    @Override
    public void setPrgList(List<PrgState> newPrgList) {
        stateList.clear();
        stateList.addAll(newPrgList);
    }
}
