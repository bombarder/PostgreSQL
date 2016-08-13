package ua.com.juja.sqlcmd;

public interface JDBCDatabaseManager {
    DataSet[] getTableData(String tableName);

    String[] getTableNames();

    void connect(String database, String user, String password);

    void clear(String tableName);

    void create(DataSet input);

    void update(String tableName, int id, DataSet newValue);
}
