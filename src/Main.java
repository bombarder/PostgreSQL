import java.sql.*;
import java.util.Random;

public class Main {
    public static void main(String[] argv) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres",
                    "2005980");
        //insert
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO users(name, password) " + "VALUES ('Stiven Pupkin', '1111')");
        statement.close();

        //select
        statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id > 1");
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("------");
        }
        rs.close();
        statement.close();

        //delete
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM users WHERE id < 2");
        statement.close();

        //update
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE users SET password = ? WHERE id > 4");
        String pass = "password" + new Random().nextInt();
        ps.setString(1, pass);
        ps.executeUpdate();

        statement.close();
        connection.close();
    }
}