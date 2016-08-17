package ua.com.juja.sqlcmd.Controller;

import ua.com.juja.sqlcmd.Controller.command.*;
import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

public class MainController {

    private Command[] commands;
    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
        this.commands = new Command[]{new Exit(view), new Help(view), new List(view, manager),
                new Find(view, manager)};

    }

    public void run() {
        connectToDB();

        while (true) {
            view.write("Input commands or help.");
            String command = view.read();
            if (commands[3].canProcess(command)) {
                commands[3].process(command);
            } else if (commands[2].canProcess(command)) {
                commands[2].process(command);
            } else if (commands[1].canProcess(command)) {
                commands[1].process(command);
            } else if (commands[0].canProcess(command)) {
                commands[0].process(command);
            } else {
                view.write("This commands doesn't exist " + command);
            }
        }
    }

    private void connectToDB() {
        view.write("hi, user!");
        view.write("Please, enter database in format: name|login|password");

        while (true) {
            try {
                String userInput = view.read();
                String[] data = userInput.split("[|]");

                if (data.length != 3) {
                    throw new IllegalArgumentException("Input is not correct with sign '|', " +
                            "waiting for 3, but have " + data.length);
                }

                String databaseName = data[0];
                String userName = data[1];
                String password = data[2];

                manager.connect(databaseName, userName, password);
                break;
            } catch (Exception e) {
                printWarning(e);
            }

        }
        view.write("You've successfully connected to the database!");
    }

    private void printWarning(Exception e) {
        String message = e.getMessage();
        if (e.getCause() != null) {
            message += " " + e.getCause().getMessage();
        }
        view.write("Not success, " + message);
        view.write("please, try again...");
    }
}

