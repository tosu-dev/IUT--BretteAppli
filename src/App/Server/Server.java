package App.Server;

import java.sql.*;

public class Server {

    public static void main(String[] args) {

        System.out.println("Hello, i'm the server side !");

        try {
            Class<?> cls = Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bretteapplidb?user=root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
