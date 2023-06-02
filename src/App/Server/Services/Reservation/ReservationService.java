package App.Server.Services.Reservation;

import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;

public class ReservationService extends Service {

    public ReservationService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {
        System.out.println(this.read());
    }
}
