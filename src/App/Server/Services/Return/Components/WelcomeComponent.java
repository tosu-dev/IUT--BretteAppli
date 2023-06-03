package App.Server.Services.Return.Components;

import App.Server.Entities.AbstractDocument;
import App.Server.Utils.EntityUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

public class WelcomeComponent implements Component {

    @Override
    public void call(Service service) {
        StringBuilder sb = new StringBuilder();

        sb.append("[SERVICE DE RETOUR] :")
          .append(System.lineSeparator())
          .append("Bienvenue, sur le service de retour. Voici la liste des documents :")
          .append(System.lineSeparator());

        sb.append(EntityUtils.showEntityListToString(AbstractDocument.class))
          .append(System.lineSeparator())
          .append("Veuillez choisir le numéro du livre que vous souhaitez retourner :");

        service.send(sb.toString());
    }
}
