package App.Server.Services;

import java.net.Socket;

public class ReturnService extends Service {

    public ReturnService(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        System.out.println("Service de retour");
    }
}
