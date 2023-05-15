package App.Server.Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Manager {

    public static Connection connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bretteapplidb?user=root");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
