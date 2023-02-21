package View.Command;

import Controller.Controller;
import Exception.MyException;

import java.io.IOException;

public class RunExample extends Command{
    private Controller ctr;

    public RunExample(String key, String descr, Controller ctr) {
        super(key, descr);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allStep2();
        } catch (MyException me) {
            System.out.println(me.getMessage());
        }
    }
}
