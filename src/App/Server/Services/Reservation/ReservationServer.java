package App.Server.Services.Reservation;

import Librairies.Servers.Server;

import java.io.IOException;

public class ReservationServer extends Server {

    private static final int PORT = 1000;
    private static final Class<ReservationService> serviceClass = ReservationService.class;

    public ReservationServer() throws IOException {
        super(serviceClass, PORT);
    }
}
