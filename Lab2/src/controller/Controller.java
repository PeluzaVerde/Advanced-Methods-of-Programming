package controller;

import model.MyInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import repo.Repo;

public class Controller {

    Repo repo = new Repo();

    public void add(MyInterface vehicle){
        repo.add(vehicle);

    }

    public void remove(int pos){
        repo.remove(pos);
    }
    public List<MyInterface> filterByColor(){
        List<MyInterface> result = new ArrayList<>();
        //for(int i=1; i<repo.getAll().length;i++)

        List<MyInterface> allVehicles= Arrays.asList(repo.getAll());//repo.getAll();

        for(MyInterface vehicle: allVehicles)
            if(vehicle!=null && Objects.equals(vehicle.getColor(), "red"))
                result.add(vehicle);

        return result;
    }
}
