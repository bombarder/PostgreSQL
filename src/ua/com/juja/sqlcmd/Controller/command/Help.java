package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;

public class Help implements Command {

    private View view;

    public Help(View view){
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("help");
    }

    @Override
    public void process(String command) {
        view.write("Existing commands:");

        view.write("connect|databaseName|username|password");
        view.write("\tfor the connection to the database");

        view.write("\tlist");
        view.write("\t\tget list of all database tables");

        view.write("\tfind|tableName");
        view.write("\t\tget tables data from");

        view.write("\thelp");
        view.write("\t\tfor list output on the screen");

        view.write("\texit");
        view.write("\t\texit from programme");
    }
}
