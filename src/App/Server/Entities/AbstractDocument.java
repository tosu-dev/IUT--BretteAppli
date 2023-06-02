package App.Server.Entities;

import App.Server.Entities.Interfaces.Document;
import App.Server.Entities.Interfaces.Entity;

public abstract class AbstractDocument implements Document, Entity {

    int numero;
    String title;

    public int getId() {
        return this.numero();
    }

    public int numero() {
        return numero;
    }

    public String getTitle() {
        return title;
    }
}
