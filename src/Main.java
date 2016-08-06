import java.sql.*;

public class Main {
    public static void main(String[] argv) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres",
                    "2005980");
        //insert
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO users(name, password) " + "VALUES ('Stiven Pupkin', '1111')";
        statement.executeUpdate(sql);

        //select
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id > 1");
        while (rs.next()) {
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
        }
        rs.close();
        statement.close();
    }
}