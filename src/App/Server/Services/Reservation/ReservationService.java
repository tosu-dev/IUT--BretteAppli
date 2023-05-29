package App.Server.Services.Reservation;

import App.Server.Entities.Abonne;
import App.Server.Entities.Entity;
import App.Server.Models.Model;
import App.Server.Models.SuscriberModel;
import Librairies.Servers.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ReservationService extends Service {

    public ReservationService(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    protected void execute() throws IOException {
        String read = this.getProtocol().read();
        this.getProtocol().send("Hello ! You sent me : " + read);
    }
}
