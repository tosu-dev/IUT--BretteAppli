package App.Server.Entities;

import App.Server.Models.DocumentModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dvd extends AbstractDocument {

    private final static String PREFIX = "DVD-";

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

    @Override
    public String getIdentifier() {
        return PREFIX + numero;
    }

    @Override
    public void save() throws SQLException {
        new DocumentModel().save();
    }
}
