package App.Server.Managers;

import Librairies.Servers.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class ServerManager {

    public static Server start(Class<? extends Server> serverToStart) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Server server = serverToStart.getConstructor().newInstance(); //All declarable server need to have default parameters.

        server.start();
        System.out.println("Starting server \"" + server.getName() + "\", on " + server.getIp());

        return server;
    }

    public static void stop(Server serverToStop) throws IOException {
        serverToStop.stop();
    }

}
