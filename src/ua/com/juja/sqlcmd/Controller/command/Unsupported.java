package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;

public class Unsupported implements Command {
    private View view;

    public Unsupported(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("This commands doesn't exist " + command);
    }
}
