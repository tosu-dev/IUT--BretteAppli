package App.Server;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            new Thread(new ReservationServer()).start();
			System.out.println("Serveur de réservation lancé");

            new Thread(new BorrowServer()).start();
			System.out.println("Serveur d'emprunt lancé");

            new Thread(new ReturnServer()).start();
			System.out.println("Serveur de retour lancé");

		} catch (IOException e) {
				System.err.println("Problème lors de la création du serveur : " +  e);
		}
    }
}
