package firstApi.com.example.DockerMongoExTcLombkWeb;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {
    private static String url = "jdbc:postgresql://localhost:5432/users";
    private static String user = "user";
    private static String password = "password";

    public PostgresDB() {
    }
     public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url,user,password);
    return connection;
    }
}
