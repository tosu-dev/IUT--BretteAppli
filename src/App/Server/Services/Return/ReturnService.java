package App.Server.Services.Return;

import Librairies.Servers.Service;

import java.io.IOException;
import java.net.Socket;

public class ReturnService extends Service {

    public ReturnService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {
        System.out.println(this.read());
    }
}
