package App.Server.Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static Connection connectionInstance;

    private static String CONNECTION_HOST = "localhost";
    private static int CONNECTION_PORT = 3306;
    private static String CONNECTION_USER = "root";
    private static String CONNECTION_PASSWORD = "";
    private static String CONNECTION_DATABASE = "bretteapplidb";

    public static void setHost(String connectionHost) {
        CONNECTION_HOST = connectionHost;
    }

    public static void setPort(int connectionPort) {
        CONNECTION_PORT = connectionPort;
    }

    public static void setUser(String connectionUser) {
        CONNECTION_USER = connectionUser;
    }

    public static void setPassword(String connectionPassword) {
        CONNECTION_PASSWORD = connectionPassword;
    }

    public static void setDatabase(String connectionDatabase) {
        CONNECTION_DATABASE = connectionDatabase;
    }

    private static Connection generateConnection() throws SQLException {
        String CONNECTION_URL = "jdbc:mysql://" + CONNECTION_HOST + ":" + CONNECTION_PORT + "/" + CONNECTION_DATABASE;

        return DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
    }

    public static Connection connect() {
        try {
            // TODO : check is connected
            if (connectionInstance != null && !connectionInstance.isClosed()) return connectionInstance;

            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionInstance = DatabaseManager.generateConnection();
            connectionInstance.setAutoCommit(false);
            return connectionInstance;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // disconnect (optional)
}
