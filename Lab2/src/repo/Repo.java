package repo;

import model.MyInterface;
import exceptions.Exceptions;
public class Repo implements RepoInterface{
    private MyInterface[] arr;
    private int cap;
    private int size;

    public Repo() {
        this.arr = new MyInterface[50];
        this.cap = 50;
        this.size = 0;
    }

    @Override
    public void add(MyInterface vehicle){
        if(this.size==this.cap)
            throw new Exceptions("Capacity exceeded");
        this.arr[size]=vehicle;
        size++;

    }

    @Override
    public void remove(int pos) {
        if(this.arr[pos]==null)
            throw new Exceptions("Doesnt Exist");
        this.arr[pos]=this.arr[size-1];
        this.arr[size-1]=null;
        size--;

    }

    @Override
    public MyInterface[] getAll() {
        return this.arr;
    }
}
