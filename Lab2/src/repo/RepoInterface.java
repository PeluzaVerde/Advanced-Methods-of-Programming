package repo;

import exceptions.Exceptions;
import model.MyInterface;

public interface RepoInterface {
    public void add(MyInterface vehicle) throws Exceptions;
    public void remove(int pos);
    public MyInterface[] getAll();
}
