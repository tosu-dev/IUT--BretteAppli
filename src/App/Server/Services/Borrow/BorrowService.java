package App.Server.Services.Borrow;

import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;

public class BorrowService extends Service {

    public BorrowService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {

    }

    @Override
    public void run() {
        System.out.println("Service d'emprunt");
    }
}
