package App.Server.Services.Reservation.Components;

import App.Server.Entities.Abonne;
import App.Server.Entities.AbstractDocument;
import App.Server.Exceptions.CustomException;
import App.Server.Exceptions.IllegalBookingException;
import App.Server.Exceptions.IllegalBorrowException;
import App.Server.Managers.ReminderManager;
import App.Server.Managers.TimerTaskManager;
import App.Server.Timers.StopMusicTimer;
import App.Server.Utils.ProtocolUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;

public class ReserveBookComponent implements Component {

    private String getMusicPlayerTimerName(AbstractDocument document, Abonne subscriber) {
        return document.getIdentifier() + subscriber.getIdentifier() + StopMusicTimer.getInstance().getName();
    }

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
        } finally {
            service.stopWaiting();
        }
    }

    private void sendMusic(Service service, AbstractDocument document, Abonne subscriber) {
        try {
            service.send("[STARTMUSIC] Votre document est actuellement réservé, voici de la musique en attendant... [SEND]");
            TimerTaskManager.schedule(this.getMusicPlayerTimerName(document, subscriber), new StopMusicTimer(service, this, document, subscriber));

            service.read();
        } catch (IOException ignored) {
        }
    }

    public void stopMusic(Service service, AbstractDocument document, Abonne subscriber) {
        try {
            service.send("[STOPMUSIC][SEND]");
            service.read();

            try {
                document.reservation(subscriber);
                service.send("Félicitation ! Vous avez bien réservé " + document.getTitle());
            } catch (CustomException e) {
                service.send("Malheureusent, vous ne pouvez pas réserver ce document, une personne a été plus rapide que vous !" + System.lineSeparator() + "En espérant que vous aurez profité de la musique !");
            }
        } catch (IOException ignored) {
        } finally {
            service.stopWaiting();
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

            service.stopWaiting();

        } catch (IllegalBorrowException e) {
            this.askReminder(service, document, subscriber);
        } catch (IllegalBookingException e) {
            this.sendMusic(service, document, subscriber);
        } catch (CustomException e) {
            service.send(e.errorMessage());
        } catch (IOException ignored) {
        }

    }

}
