package App.Server.Services.Return.Components;

import App.Server.Entities.AbstractDocument;
import App.Server.Exceptions.CustomException;
import App.Server.Managers.EmailManager;
import App.Server.Managers.ReminderManager;
import App.Server.Utils.ProtocolUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ReturnBookComponent implements Component {

    private void sendReminders(AbstractDocument document) {
        ReminderManager.getReminders(document).forEach(reminder -> {
            String message = "Bonjour " + reminder.getFullName() + System.lineSeparator() +
                             "Le document " + document.getTitle() + " que vous avez réservé est maintenant disponible !" + System.lineSeparator() +
                             "Vous pouvez venir le chercher à la bibliothèque." + System.lineSeparator();
            try {
                EmailManager.send("illutech.badiiix@gmail.com", "[Rappel] Un document que vous souhaitez viens de revenir !", message);
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void call(Service service) {
        try {
            AbstractDocument document = (AbstractDocument) ProtocolUtils.askEntityById(service, AbstractDocument.class, "Numéro du document incorrect. Vueillez réessayer.", "Entrez le numéro du document que vous souhaitez retourner :");

            //Todo , vérifier si le document est retourné trop tard / est abimé [Ajouter de l'aléatoire pour le simuler]
            document.retour();
            service.send("Félicitation ! Vous avez bien retourné " + document.getTitle());

            this.sendReminders(document);

        } catch (CustomException e) {
            service.send(e.errorMessage());
        } catch (IOException ignored) {
        } finally {
            service.stopWaiting();
        }

    }
}
