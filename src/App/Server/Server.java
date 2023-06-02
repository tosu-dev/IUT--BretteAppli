package App.Server;

import App.Server.Factories.BigDataFactory;
import App.Server.Factories.DatabaseFactory;
import App.Server.Factories.ServerFactory;
import App.Server.Managers.BigDataManager;

import java.lang.reflect.InvocationTargetException;

public class Server {
    public static void main(String[] args) {
        try {
            ServerFactory.launch();
            DatabaseFactory.setup();
            BigDataFactory.create();
		} catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
