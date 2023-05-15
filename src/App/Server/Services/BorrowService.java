package App.Server.Services;

import java.net.Socket;

public class BorrowService extends Service {

    public BorrowService(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        System.out.println("Service d'emprunt");
    }
}
