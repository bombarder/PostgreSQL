package ua.com.juja.sqlcmd.Controller;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.Arrays;

public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }
    public void run(){
        connectToDB();

        while (true){
            view.write("Input command or help.");
            String command = view.read();

            if (command.equals("list")){
                doList();
            } else if (command.equals("help")){
                doHelp();
            } else {
                view.write("This command doesn't exist " + command);
            }
        }
    }

    private void doHelp() {
        view.write("Existing commands:");
        view.write("\tlist");
        view.write("\t\tget list of all database tables");
        view.write("\thelp");
        view.write("\t\tfor list output on the screen");
    }

    private void doList() {
        String[] tableNames = manager.getTableNames();

        String message = Arrays.toString(tableNames);

        view.write(message);
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
