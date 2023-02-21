import controller.Controller;
import exceptions.Exceptions;
import model.*;
import repo.Repo;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Controller myController = new Controller();
        //Repo repo = new Repo();
        try{
        myController.add(new Bicycle(0,"red"));
        myController.add(new Car(1,"red"));
        myController.add(new Motorcycle(2,"blue"));
        myController.add(new Car(3,"red"));
        myController.add(new Bicycle(4,"orange"));
        myController.add(new Motorcycle(5,"red"));
        myController.add(new Car(6,"GOLDEN"));
        myController.remove(1);
        myController.remove(0);//

        myController.remove(46);


        }catch (Exceptions e){
            System.out.println(e);
        }
        List<MyInterface> filterByColor = myController.filterByColor();
        for(MyInterface vehicle:filterByColor){
            System.out.println(vehicle);
        }
    }
}