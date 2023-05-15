package App.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final static String HOST = "localhost";

    public static void main(String[] args) {
        System.out.println("Hello, i'm the client !");

        Socket socket = null;
		try {

			BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Voulez-vous : (1)réserver (2)emprunter (3)retourner");
			int choice = Integer.parseInt(kb.readLine());

			int port = 1000 + choice - 1;
			System.out.println(port);

			socket = new Socket(HOST, port);
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
			PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
			System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());

			if (choice == 1 || choice == 2) {
				System.out.print("Connectez-vous : ");
				String idSuscriber = kb.readLine();

				System.out.print("\nChoisissez un document :");
				String idDocument = kb.readLine();

				sout.println(idSuscriber + " " + idDocument);
			} else if (choice == 3) {
				System.out.print("\nChoisissez un document :");
				String idDocument = kb.readLine();

				sout.println(idDocument);
			}

			socket.close();
		} catch (IOException e) {
            System.err.println(e);
        }

		try {
            if (socket != null) socket.close();
        } catch (IOException e2) { ; }
	}
}
