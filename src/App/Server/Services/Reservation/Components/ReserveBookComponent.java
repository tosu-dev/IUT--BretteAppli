package App.Server.Services.Reservation.Components;

import App.Server.Entities.Abonne;
import App.Server.Entities.AbstractDocument;
import App.Server.Exceptions.CustomException;
import App.Server.Utils.ProtocolUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;

public class ReserveBookComponent implements Component {


    @Override
    public void call(Service service) {

        try {
            Abonne subscriber = (Abonne) ProtocolUtils.askEntityById(service, Abonne.class, "Numéro client incorrect. Vueillez réessayer.", "Entrez votre numéro client :");

            String askDocument = "Bonjour " +
                                 subscriber.getFullName() +
                                 " " +
                                 System.lineSeparator() +
                                 "Veuillez entrer le numéro du document que vous souhaitez :";

            service.send(askDocument);

            AbstractDocument document = (AbstractDocument) ProtocolUtils.askEntityById(service, AbstractDocument.class, "Numéro du document incorrect. Vueillez réessayer.", "Entrez le numéro du document que vous souhaitez:");

            document.reservation(subscriber);
            service.send("Félicitation ! Vous avez bien réservé " + document.getTitle());

        } catch (CustomException e) {
            service.send(e.errorMessage());
        } catch (IOException ignored) {
        }

    }

}
