package App.Server;

import App.Server.Services.ReservationService;

import java.io.IOException;

public class ReservationServer extends Server {

    public static final int PORT = 1000;

    public ReservationServer() throws IOException {
        super(PORT);
    }

    @Override
    public void run() {
		try {
            System.out.println("Serveur lancé sur le port " + PORT);
			while(true) {
                new ReservationService(socket.accept()).start();
            }
		} catch (Exception e) { // TODO : changer Exception
			try {
                this.socket.close();
            } catch (IOException e_) { ; }

			System.err.println("Pb sur le port d'écoute :" + e);
		}
    }
}
