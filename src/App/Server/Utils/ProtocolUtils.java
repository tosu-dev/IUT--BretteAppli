package App.Server.Utils;

import App.Server.Entities.Interfaces.Entity;
import Librairies.Servers.Service;

import java.io.IOException;

public class ProtocolUtils {

    private static final String DEFAULT_MESSAGE_ERROR = "Entrée incorrecte, veuillez réessayer.";
    private static final String DEFAULT_ASK_ERROR = "Entrez votre valeur : ";

    public static String askToRetry(Service service) throws IOException {
        return ProtocolUtils.askToRetry(service, DEFAULT_MESSAGE_ERROR, DEFAULT_ASK_ERROR);
    }

    public static String askToRetry(Service service, String messageError, String askMessage) throws IOException {
        service.send(messageError + System.lineSeparator() + askMessage);

        return service.read();
    }

    public static Entity askEntityById(Service service, Class<? extends Entity> entity, String messageError, String askMessage) throws IOException {

        Integer documentId = IntegerUtils.toInteger(service.read());
        Entity  entityToReturn;

        while (documentId == null || (entityToReturn = EntityUtils.getEntityById(entity, documentId)) == null) {
            documentId = IntegerUtils.toInteger(ProtocolUtils.askToRetry(service, messageError, askMessage));
        }

        return entityToReturn;
    }

    public static boolean askBoolean(Service service, String messageError, String askMessage) throws IOException {
        String answer = service.read();

        while (!answer.equals("oui") && !answer.equals("non")) {
            answer = ProtocolUtils.askToRetry(service, messageError, askMessage);
        }

        return answer.equals("oui");
    }

}
