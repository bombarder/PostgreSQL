package ua.com.juja.sqlcmd.Controller;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }
    public void run(){
        connectToDB();
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
