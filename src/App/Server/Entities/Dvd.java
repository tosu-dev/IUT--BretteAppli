package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Exceptions.AgeRestrictionException;
import App.Server.Models.DocumentModel;
import App.Server.Models.DvdModel;
import App.Server.Utils.EntityUtils;
import App.Server.Utils.ageUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dvd extends AbstractDocument {

    private final static String   PREFIX = "DVD-";
    private              Document document;

    private boolean adult;

    @Override
    public String getTitle() {
        return this.document.getTitle();
    }

    public Abonne empruntePar() {
        return this.document.empruntePar();
    }

    public Abonne reservePar() {
        return this.document.reservePar();
    }

    public void reservation(Abonne ab) {
        if (this.adult && !ageUtils.hasAge(ab.getBirthdate())) {
            throw new AgeRestrictionException();
        }

        this.document.reservation(ab);
    }

    public void emprunt(Abonne ab) {
        if (this.adult && !ageUtils.hasAge(ab.getBirthdate())) {
            throw new AgeRestrictionException();
        }

        this.document.emprunt(ab);
    }

    public void retour() {
        this.document.retour();
    }

    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.numero = res.getInt("id");
        this.document = (Document) EntityUtils.getEntityById(Document.class, this.numero, false);
        this.adult = res.getBoolean("adult");

        return this;
    }

    public String getIdentifier() {
        return PREFIX + numero;
    }

    public void save() throws SQLException {
        new DvdModel().save(this);
    }

    @Override
    public String toString() {
        return this.document.toString() + ((this.adult) ? " {+16} " : " {tout public} ");
    }
}
