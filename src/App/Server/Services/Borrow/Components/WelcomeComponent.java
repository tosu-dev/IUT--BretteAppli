package App.Server.Services.Borrow.Components;

import App.Server.Entities.AbstractDocument;
import App.Server.Utils.EntityUtils;
import Librairies.Servers.Component;
import Librairies.Servers.Service;

public class WelcomeComponent implements Component {

    @Override
    public void call(Service service) {

        StringBuilder sb = new StringBuilder();

        sb.append("[SERVICE D'EMPRUN] :")
          .append(System.lineSeparator())
          .append("Bienvenue, sur le service d'emprun. Voici la liste des documents disponible dans notre médiathèque")
          .append(System.lineSeparator());

        sb.append(EntityUtils.showEntityListToString(AbstractDocument.class)) //Nous voulons tous les documents ici.
          .append(System.lineSeparator())
          .append("Pour emprunter, veuillez entrer votre numéro client :");

        service.send(sb.toString());
    }

}
