package App.Server.Services.Reservation;

import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReservationService extends Service {

    public ReservationService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected List<Class<? extends Component>> componentList() {
        return null;
    }
}
