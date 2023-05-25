package App.Server.Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// databasemanager
public class DatabaseManager {

    public static Connection connect;

    public static Connection connect() {
        try {
            // TODO : check is connected
            if (connect != null) return connect;

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bretteapplidb?user=root");
            connect.setAutoCommit(false);
            return connect;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // disconnect (optional)
}
