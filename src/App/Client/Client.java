package App.Client;

import App.Client.Factories.ClientFactory;
import App.Client.Managers.ClientManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            ClientManager clientManager = ClientFactory.create(args);

            ClientFactory.manage(clientManager);

            ClientFactory.close(clientManager);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 IOException e) {
            throw new RuntimeException(e);
        }
    }
}
