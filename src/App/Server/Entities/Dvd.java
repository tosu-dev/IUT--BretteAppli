package App.Server.Entities;

public class Dvd extends AbstractDocument {

    private boolean adult;

    @Override
    public Abonne empruntePar() {
        return null;
    }

    @Override
    public Abonne reservePar() {
        return null;
    }

    @Override
    public void reservation(Abonne ab) {

    }

    @Override
    public void emprunt(Abonne ab) {

    }

    @Override
    public void retour() {

    }
}
