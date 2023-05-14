package App.Server;

import java.io.IOException;
import java.sql.*;
import java.net.*;

public class Server implements Runnable {

    private int port;
    private ServerSocket socket;

    public Server(int port) throws IOException {
        this.port = port;
        this.socket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
		try {
			while(true) {
                // ... SERVICES ...
            }
		}
        // TODO : changer Exception
		catch (Exception e) {
			try {
                this.socket.close();
            }
            catch (IOException e_) {

            }
			System.err.println("Pb sur le port d'écoute :" + e);
		}
    }

    protected void finalize() throws Throwable {
		try {
            this.socket.close();
        }
        catch (IOException e) {

        }
	}

    public static void main(String[] args) {

        System.out.println("Hello, i'm the server side !");

        // [Théo] : Je sais pas où mettre ce code je le laisse là pour l'instant
        // TODO : mettre ce code au bon endroit
        try {
            Class<?> cls = Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bretteapplidb?user=root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
