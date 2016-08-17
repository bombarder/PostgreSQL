package ua.com.juja.sqlcmd.Controller.command;

import ua.com.juja.sqlcmd.View.View;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;

public class Find implements Command {

    private View view;
    private DatabaseManager manager;

    public Find(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("find|users");
    }
    @Override
    public void process(String command) {
        String[] data = command.split("[|]");
        String tableName = data[1];

        String[] tableColumns = manager.getTableColumns(tableName);
        printHeader(tableColumns);

        DataSet[] tableData = manager.getTableData(tableName);
        printTableData(tableData);
    }

    private void printRow(DataSet row) {
        Object[] values = row.getValues();
        String result = "|";
        for (Object value : values) {
            result += value + "|";
        }
        view.write(result);
    }

    private void printTableData(DataSet[] tableData) {
        for (DataSet row : tableData) {
            printRow(row);
        }
    }
    private void printHeader(String[] tableColumns) {
        String header = "|";
        for (String name : tableColumns) {
            header += name + "|";
        }
        view.write("--------------------");
        view.write(header);
        view.write("--------------------");
    }
}
