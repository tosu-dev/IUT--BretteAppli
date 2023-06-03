package App.Server.Services.Borrow;

import App.Server.Services.Borrow.Components.BorrowBookComponent;
import App.Server.Services.Borrow.Components.WelcomeComponent;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BorrowService extends Service {

    public BorrowService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected List<Class<? extends Component>> componentList() {
        ArrayList<Class<? extends Component>> componentsToLaunch = new ArrayList<>();

        componentsToLaunch.add(WelcomeComponent.class);
        componentsToLaunch.add(BorrowBookComponent.class);

        return componentsToLaunch;
    }
}
