package Controller;
import Exception.MyException;
import Model.ProgramState.PrgState;

public class P2 {
    final PrgState first;
    final MyException second;

    P2(PrgState _first, MyException _second) {
        first = _first;
        second = _second;
    }
}