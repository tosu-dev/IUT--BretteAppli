package App.Server.Services.Borrow;

import App.Server.Services.Borrow.BorrowService;
import App.Server.Services.Reservation.ReservationService;
import Librairies.Servers.Server;

import java.io.IOException;

public class BorrowServer extends Server {

    private static final int PORT = 1001;
    private static final Class<BorrowService> serviceClass = BorrowService.class;


    public BorrowServer() throws IOException {
        super(serviceClass, PORT);
    }
}
