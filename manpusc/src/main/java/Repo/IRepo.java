package Repo;

import Model.ProgramState.PrgState;

import java.io.IOException;
import Exception.MyException;
import java.util.List;

public interface IRepo {
    //List<PrgState> getCrtPrg();

    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newPrgList);
    void addPrgState(PrgState state);
    void logPrgStateExec(PrgState state) throws MyException, IOException;
    //void setPrgList(List<PrgState> prgList);
}
