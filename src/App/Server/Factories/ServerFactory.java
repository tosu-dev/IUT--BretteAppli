package App.Server.Factories;

import App.Server.Managers.ServerManager;
import App.Server.Services.Borrow.BorrowServer;
import App.Server.Services.Reservation.ReservationServer;
import App.Server.Services.Return.ReturnServer;
import Librairies.Servers.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Vector;

public class ServerFactory {

    private static final Vector<Server> startedServers = new Vector<>();

    public static void launch() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ArrayList<Class<? extends Server>> serversList = new ArrayList<>();

        serversList.add(BorrowServer.class);
        serversList.add(ReservationServer.class);
        serversList.add(ReturnServer.class);

        for(Class<? extends Server> serverClass : serversList) {
            Server server = ServerManager.start(serverClass);
            startedServers.add(server);
        }
    }

    public static void close() throws IOException {
        for(Server server : startedServers) {
            ServerManager.stop(server);
        }
    }

}
