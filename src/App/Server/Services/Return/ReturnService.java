package App.Server.Services.Return;

import App.Server.Services.Return.Components.ReturnBookComponent;
import App.Server.Services.Return.Components.WelcomeComponent;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ReturnService extends Service {

    public ReturnService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected List<Class<? extends Component>> componentList() {
        ArrayList<Class<? extends Component>> componentsToLaunch = new ArrayList<>();

        componentsToLaunch.add(WelcomeComponent.class);
        componentsToLaunch.add(ReturnBookComponent.class);

        return componentsToLaunch;
    }
}
