package App.Server.Entities;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Override
    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.numero = res.getInt("id");
        this.title = res.getString("title");
        this.adult = res.getBoolean("adult");

        return null;
    }
}
