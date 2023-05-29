package App.Server;

import App.Server.Factories.ServerFactory;
import App.Server.Managers.ServerManager;
import App.Server.Services.Borrow.BorrowServer;
import App.Server.Services.Reservation.ReservationServer;
import App.Server.Services.Return.ReturnServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) {
        try {
            ServerFactory.launch();
		} catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
