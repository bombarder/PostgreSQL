package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DatabaseManager;

public class IsConnected implements Command {

    private DatabaseManager manager;
    private View view;

    public IsConnected(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;

    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConected();
    }

    @Override
    public void process(String command) {
        view.write(String.format("You can't use commands '%s' while is not connected -- " +
                "database name|login|password", command));
    }
}
