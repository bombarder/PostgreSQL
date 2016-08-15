package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;

public class Exit  implements Command{

    private View view;

    public Exit(View view){
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("exit");
    }

    @Override
    public void process(String command) {
        view.write("Bye, bye...");
        System.exit(0);
    }
}
