package View;

import View.Command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;

    public TextMenu(){commands = new HashMap<>();}

    public void addCommand(Command c){
        commands.put(c.getKey(),c);
    }

    public void printMenu(){
        for(Command com:commands.values()){
            String line = String.format("%4s : %s",com.getKey(),com.getDescr());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner  = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Input the option: ");
            String key = scanner.nextLine();
            Command com = commands.get(key);
            if(com == null)
            {
                System.out.println("Invalid option");
                continue;
            }
            com.execute();
        }
    }
}
