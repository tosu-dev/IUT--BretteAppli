package App.Server.Utils;

import App.Server.Entities.Interfaces.Entity;
import Librairies.Communication.Protocol;
import Librairies.Servers.Service;

import java.io.IOException;

public class ProtocolUtils {

    private static final String DEFAULT_MESSAGE_ERROR = "Entrée incorrecte, veuillez réessayer.";

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

}
