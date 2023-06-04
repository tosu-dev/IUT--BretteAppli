package App.Server.Services.Return;

import Librairies.Servers.Server;

import java.io.IOException;

public class ReturnServer extends Server {

    private static final int                  PORT          = 1002;
    private static final String               NAME          = "Return";
    private static final Class<ReturnService> SERVICE_CLASS = ReturnService.class;

    public ReturnServer() throws IOException {
        super(SERVICE_CLASS, PORT, NAME);
    }
}
