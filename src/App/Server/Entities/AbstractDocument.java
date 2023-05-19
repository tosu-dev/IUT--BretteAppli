package App.Server.Entities;

public abstract class AbstractDocument implements Document, Entity {

    int numero;
    String title;

    @Override
    public int numero() {
        return numero;
    }

    public String getTitle() {
        return title;
    }
}
