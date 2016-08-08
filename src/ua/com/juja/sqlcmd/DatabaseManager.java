package ua.com.juja.sqlcmd;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

public class DatabaseManager {

    private Connection connection;

    public static void main(String[] argv) throws ClassNotFoundException, SQLException {

        String database = "sqlcmd";
        String user = "postgres";
        String password = "2005980";

        DatabaseManager manager = new DatabaseManager();
        manager.connect(database, user, password);

        Connection connection = manager.getConnection();

        //all tables
        String[] tables = manager.getTables();
        System.out.println(Arrays.toString(tables));

        //insert
        String insert = "INSERT INTO users(name, password) " +
                "VALUES ('Stiven Pupkin', '1111')";
        manager.delete(insert);

        //select users
        String tableName = "users";

//        String select = "SELECT * FROM " + tableName;
//        manager.select(connection, select);

        //select all tables
        String selectAllTables = "SELECT COUNT(*) FROM " + tableName;
        manager.selectAllTables(selectAllTables);

        //delete
        String delete = "DELETE FROM users WHERE id < 9";
        manager.delete(delete);

        //update
        String update = "UPDATE users SET password = ? WHERE id > 4";
        String pass = "password" + new Random().nextInt();
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setString(1, pass);
        ps.executeUpdate();

        connection.close();
    }

    public String[] getTables() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT table_name FROM information_schema.tables " +
                    "WHERE table_schema='public' AND table_type='BASE TABLE';");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            rs.close();
            statement.close();
            return tables;

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private Connection getConnection() {
        return connection;
    }

    public void connect(String database, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Please, add jdbc jar to project. ");
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/" + database, user, password);
        } catch (SQLException e) {
            System.out.println(String.format("Can't get connection for database:%s user:%s", database, user));
            e.printStackTrace();
            connection = null;
        }
    }

    public void select(String selectQuery) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectQuery);
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("------");
        }
        rs.close();
        statement.close();
    }

    public void selectAllTables(String selectQuery) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(selectQuery);
        rs.next();
        int size = rs.getInt(1);

        Statement stmt = connection.createStatement();
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println(rsmd.getColumnName(1));
        System.out.println(rsmd.getColumnName(2));
//        System.out.println(rsmd.getColumnName(3));

        DataSet[] result = new DataSet[size];
        int index = 0;

        while (rs.next()) {
            result[index++] = new DataSet();
        }
        rs.close();
        statement.close();
    }

    public void delete(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }
}