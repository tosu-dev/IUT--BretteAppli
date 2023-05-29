package App.Client.Managers;

import Librairies.Communication.Protocol;
import Librairies.Communication.WankaProtocol;
import Librairies.Servers.Link;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class ClientManager extends Link {

    public ClientManager(String host, int port) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        super(new Socket(host, port), WankaProtocol.class);
    }

    public BufferedReader getInput() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    protected void execute() throws IOException {
    }
}
