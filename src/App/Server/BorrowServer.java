package App.Server;

import App.Server.Services.BorrowService;

import java.io.IOException;

public class BorrowServer extends Server {

    public static final int PORT = 1001;

    public BorrowServer() throws IOException {
        super(PORT);
    }

    // TODO : code redondant dans les serveurs, utiliser le design pattern "Template Method" ?
    @Override
    public void run() {
        System.out.println("Serveur lancé sur le port " + PORT);
		try {
			while(true) {
                new BorrowService(socket.accept()).start();
            }
		} catch (Exception e) { // TODO : changer Exception
			try {
                this.socket.close();
            } catch (IOException e_) { ; }

			System.err.println("Pb sur le port d'écoute :" + e);
		}
    }
}
