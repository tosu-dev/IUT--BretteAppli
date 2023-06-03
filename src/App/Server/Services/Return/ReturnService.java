package App.Server.Services.Return;

import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReturnService extends Service {

    public ReturnService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected List<Class<? extends Component>> componentList() {
        return null;
    }
}
