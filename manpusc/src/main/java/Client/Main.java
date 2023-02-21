package Client;

import Controller.Controller;
import Repo.IRepo;
import Repo.Repo;
import View.View;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        IRepo repo = new Repo(new ArrayList<>(), "C:\\Users\\HP\\Desktop\\INtellij stuff\\outputMAP.txt");
        Controller controller = new Controller(repo);
        //View view = new View(controller);
        //view.start();
    }
}