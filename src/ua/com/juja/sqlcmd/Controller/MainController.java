package ua.com.juja.sqlcmd.Controller;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.sqlcmd.View.Console;

public class MainController {
    public static void main(String[] args) {

        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();

        view.write("привет юзер!");
        view.write("Please, enter database in format: name|login|password");

        while (true) {

            String userInput = view.read();
            String[] data = userInput.split("[|]");

            String databaseName = data[0];
            String userName = data[1];
            String password = data[2];

            try {
                manager.connect(databaseName, userName, password);
                break;
            } catch (Exception e) {
                String message = e.getMessage();
                if (e.getCause() != null){
                    message += " " + e.getCause().getMessage();
                }
                view.write("not success " + message);
                view.write("please, try again...");
            }
        }
        view.write("You'ed successfully connected to the database!");
    }
}
