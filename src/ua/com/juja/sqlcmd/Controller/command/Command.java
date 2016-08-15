package ua.com.juja.sqlcmd.Controller.command;

/**
 * Created by nmakarov on 8/15/2016.
 */
public interface Command {
    boolean canProcess(String command);
    void process(String command);
}
