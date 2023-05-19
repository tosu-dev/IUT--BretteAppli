package App.Server.Services;

import App.Server.Entities.Abonne;
import App.Server.Models.SuscriberModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReservationService extends Service  {

    public ReservationService(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        System.out.println("Service de réservation");
        try {
            BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
            PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

            String line = in.readLine();
            String[] infos = line.split(" ");

            int idSuscriber = Integer.parseInt(infos[0]);
            int idDocument = Integer.parseInt(infos[1]);

            Abonne suscriber = SuscriberModel.getById(idSuscriber);

            System.out.println(suscriber.getFirstname());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
