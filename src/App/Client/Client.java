package App.Client;

import App.Client.Factories.ClientFactory;
import App.Client.Managers.ClientManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
