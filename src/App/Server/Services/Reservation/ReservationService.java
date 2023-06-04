package App.Server.Services.Reservation;

import App.Server.Services.Reservation.Components.ReserveBookComponent;
import App.Server.Services.Reservation.Components.WelcomeComponent;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ReservationService extends Service {
    public ReservationService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected List<Class<? extends Component>> componentList() {
        ArrayList<Class<? extends Component>> componentsToLaunch = new ArrayList<>();

        componentsToLaunch.add(WelcomeComponent.class);
        componentsToLaunch.add(ReserveBookComponent.class);

        return componentsToLaunch;
    }
}
