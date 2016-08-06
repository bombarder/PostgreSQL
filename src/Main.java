import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] argv) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5433/sqlcmd", "postgres",
                    "2005980");
        //insert
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO users(name, password) " + "VALUES ('Stiven Pupkin', '1111')";
        statement.executeUpdate(sql);

        //select
        statement = connection.createStatement();



    }
}