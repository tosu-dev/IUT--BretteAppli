package App.Server.Services.Reservation;

import Librairies.Servers.Server;

import java.io.IOException;

public class ReservationServer extends Server {

    private static final int                       PORT          = 1000;
    private static final String                    NAME          = "Reservation";
    private static final Class<ReservationService> SERVICE_CLASS = ReservationService.class;

    public ReservationServer() throws IOException {
        super(SERVICE_CLASS, PORT, NAME);
    }
}
