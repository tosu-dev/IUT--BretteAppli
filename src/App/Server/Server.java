package App.Server;

import App.Server.Factories.*;

import java.lang.reflect.InvocationTargetException;

public class Server {
    public static void main(String[] args) {
        try {
            ServerFactory.launch();
            DatabaseFactory.setup();
            EmailFactory.setup();

            BigDataFactory.populate();
            ReminderFactory.populate();

        } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
