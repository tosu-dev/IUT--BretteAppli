package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Models.DocumentModel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Document extends AbstractDocument {

    private final static String PREFIX = "DOC-";
    protected final      Object lock   = new Object();

    private int     state;
    private Command command;

    public void setState(DocumentState state) throws SQLException {
        this.state = state.getId();
        this.save();
    }

    public int getState() {
        return this.state;
    }

    public int getId() {
        return this.numero();
    }

    public Abonne empruntePar() {
        if (this.state == DocumentState.BORROWED.getId()) {
            return this.command.getSubscriber();
        }

        return null;
    }

    public Abonne reservePar() {
        if (this.state == DocumentState.RESERVED.getId()) {
            return this.command.getSubscriber();
        }

        return null;
    }

    public void reservation(Abonne ab) {
        //TODO preconditions & timer

        try {
            this.command = new Command(ab, this, (Date) Calendar.getInstance().getTime());
            this.setState(DocumentState.RESERVED);
        } catch (SQLException ignored) {

        }
    }

    public void emprunt(Abonne ab) {
        //TODO preconditions

        try {
            this.command = new Command(ab, this, (Date) Calendar.getInstance().getTime());
            this.setState(DocumentState.BORROWED);
        } catch (SQLException ignored) {

        }
    }

    public void retour() {
        //todo
        try {
            this.command.remove();
            this.setState(DocumentState.FREE);
        } catch (SQLException ignored) {

        }
    }

    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.numero = res.getInt("id");
        this.title = res.getString("title");
        this.state = res.getInt("state");

        return this;
    }

    public String getIdentifier() {
        return PREFIX + numero;
    }

    public void save() throws SQLException {
        new DocumentModel().save(this);
    }

}
