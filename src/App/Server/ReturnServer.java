package App.Server;

import App.Server.Services.ReturnService;

import java.io.IOException;

public class ReturnServer extends Server {

    public static final int PORT = 1002;

    public ReturnServer() throws IOException {
        super(PORT);
    }

    @Override
    public void run() {
        System.out.println("Serveur lancé sur le port " + PORT);
		try {
			while(true) {
                new ReturnService(socket.accept()).start();
            }
		} catch (Exception e) { // TODO : changer Exception
			try {
                this.socket.close();
            }
            catch (IOException e_) { ; }

			System.err.println("Pb sur le port d'écoute :" + e);
		}
    }
}
