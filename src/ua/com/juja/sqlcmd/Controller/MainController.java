package ua.com.juja.sqlcmd.Controller;

import ua.com.juja.sqlcmd.Controller.command.*;
import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

public class MainController {

    private Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new Command[]{
                new Connect(manager, view),
                new IsConnected(manager, view),
                new Exit(view),
                new Help(view),
                new List(view, manager),
                new Find(view, manager),
                new Unsupported(view)
        };
    }

    public void run() {

        while (true) {
            view.write("Input commands or help.");
            String input = view.read();

            for (Command command: commands){
                if (command.canProcess(input)){
                    command.process(input);
                    break;
                }
            }
        }
    }
}

