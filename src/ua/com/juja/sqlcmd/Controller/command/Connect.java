package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

/**
 * Created by nmakarov on 8/17/2016.
 */
public class Connect implements Command {

    private View view;
    private DatabaseManager manager;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        view.write("Hi, user!");
        view.write("Please, enter database: name|login|password");

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

