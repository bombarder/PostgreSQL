package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

import java.util.Arrays;

public class List implements Command {
    private View view;
    private DatabaseManager manager;

    public List(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("list");
    }

    @Override
    public void process(String command) {
        String[] tableNames = this.manager.getTableNames();

        String message = Arrays.toString(tableNames);

        view.write(message);
    }
}
