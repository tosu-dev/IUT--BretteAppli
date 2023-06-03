package App.Server.Services.Borrow.Components;

import App.Server.Entities.Abonne;
import App.Server.Entities.AbstractDocument;
import App.Server.Exceptions.AgeRestrictionException;
import App.Server.Exceptions.BannedUserException;
import App.Server.Exceptions.DocumentAlreadyBookedException;
import App.Server.Exceptions.IllegalBorrowException;
import App.Server.Utils.ProtocolUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BorrowBookComponent implements Component {

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

            System.out.println(document);
            document.emprunt(subscriber);
            service.send("Félicitation ! Vous avez bien emprunté " + document.getTitle());


        } catch (RuntimeException e) {
            service.send(manageException(e));
        } catch (IOException ignored) {
        }

    }

    public String manageException(RuntimeException e) {
        Map<Class<? extends RuntimeException>, String> classActions = new HashMap<>();
        classActions.put(AgeRestrictionException.class, "Vous n'avez pas l'âge requis pour ce livre");
        classActions.put(DocumentAlreadyBookedException.class, "Ce document est déjà reservé, veuillez réessayer plus tard.");
        classActions.put(IllegalBorrowException.class, "Ce document est déjà emprunté, veuillez réessayer plus tard.");
        classActions.put(BannedUserException.class, "Cet utilisateur est banni de la médiathèque. Vous n'avez pas le droit d'utiliser ce service.");

        return classActions.getOrDefault(e.getClass(), "Erreur inconnue, veuillez contacter un membre de l'équipe de la Médiathèque" + System.lineSeparator() + Arrays.toString(e.getStackTrace()));
    }

}
