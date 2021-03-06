package ua.com.juja.sqlcmd.Controller;

import ua.com.juja.sqlcmd.View.Console;
import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();

        MainController controller = new MainController(view, manager);
        controller.run();
    }
}
