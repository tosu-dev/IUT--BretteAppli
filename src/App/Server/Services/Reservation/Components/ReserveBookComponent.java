package App.Server.Services.Reservation.Components;

import App.Server.Entities.Abonne;
import App.Server.Entities.AbstractDocument;
import App.Server.Exceptions.CustomException;
import App.Server.Exceptions.IllegalBorrowException;
import App.Server.Managers.ReminderManager;
import App.Server.Utils.ProtocolUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;

public class ReserveBookComponent implements Component {

    private void askReminder(Service service, AbstractDocument document, Abonne subscriber) {
        try {
            service.send("Le document est déjà emprunté, souhaitez-vous recevoir un e-mail lorsqu'il sera retourné ?");
            boolean response = ProtocolUtils.askBoolean(service, "Veuillez répondre par 'oui' ou par 'non'", "Entrez 'oui' ou 'non' :");
            if (response && document != null && subscriber != null) {
                ReminderManager.addReminder(document, subscriber);
                service.send("Vous recevrez un e-mail lorsque" + document.getTitle() + " sera retourné.");
                return;
            }

            service.send("Vous ne recevrez pas d'e-mail lorsque" + document.getTitle() + " sera retourné.");

        } catch (IOException ignored) {
        }
    }

    @Override
    public void call(Service service) {

        Abonne           subscriber = null;
        AbstractDocument document   = null;

        try {
            subscriber = (Abonne) ProtocolUtils.askEntityById(service, Abonne.class, "Numéro client incorrect. Vueillez réessayer.", "Entrez votre numéro client :");

            String askDocument = "Bonjour " +
                                 subscriber.getFullName() +
                                 " " +
                                 System.lineSeparator() +
                                 "Veuillez entrer le numéro du document que vous souhaitez :";

            service.send(askDocument);

            document = (AbstractDocument) ProtocolUtils.askEntityById(service, AbstractDocument.class, "Numéro du document incorrect. Vueillez réessayer.", "Entrez le numéro du document que vous souhaitez:");

            document.reservation(subscriber);
            service.send("Félicitation ! Vous avez bien réservé " + document.getTitle());
        } catch (IllegalBorrowException e) {
            this.askReminder(service, document, subscriber);
        } catch (CustomException e) {
            service.send(e.errorMessage());
        } catch (IOException ignored) {
        }

    }

}
