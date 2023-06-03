package App.Server.Services.Return.Components;

import App.Server.Entities.AbstractDocument;
import App.Server.Exceptions.CustomException;
import App.Server.Utils.ProtocolUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

import java.io.IOException;

public class ReturnBookComponent implements Component {
    @Override
    public void call(Service service) {
        try {
            AbstractDocument document = (AbstractDocument) ProtocolUtils.askEntityById(service, AbstractDocument.class, "Numéro du document incorrect. Vueillez réessayer.", "Entrez le numéro du document que vous souhaitez retourner :");

            document.retour();
            service.send("Félicitation ! Vous avez bien retourné " + document.getTitle());

        } catch (CustomException e) {
            service.send(e.errorMessage());
        } catch (IOException ignored) {
        }
    }
}
