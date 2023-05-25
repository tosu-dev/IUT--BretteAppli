package App.Server.Services.Return;

import App.Server.Services.Borrow.BorrowService;
import App.Server.Services.Return.ReturnService;
import Librairies.Servers.Server;

import java.io.IOException;

public class ReturnServer extends Server {

    private static final int PORT = 1002;
    private static final Class<ReturnService> serviceClass = ReturnService.class;

    public ReturnServer() throws IOException {
        super(serviceClass, PORT);
    }
}
