package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Models.CommandModel;
import App.Server.Utils.EntityUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Command implements Entity {

    private static final String PREFIX = "COM-";

    private int      id = 0;
    private Abonne   subscriber;
    private Document document;
    private Date     date;

    public Command() {
    }

    public Command(Abonne subscriber, Document document, Date date) throws SQLException {
        super();
        this.subscriber = subscriber;
        this.document = document;
        this.date = date;

        if (this.subscriber != null && this.document != null && this.date != null) {
            this.save();
        }
    }

    public void setId(int id) {
        synchronized (this) {
            this.id = id;
        }
    }

    public int getId() {
        synchronized (this) {
            return this.id;
        }
    }

    public Abonne getSubscriber() {
        synchronized (this) {
            return subscriber;
        }
    }

    public Document getDocument() {
        synchronized (this) {
            return document;
        }
    }

    public Date getDate() {
        synchronized (this) {
            return date;
        }
    }

    public void remove() throws SQLException {
        synchronized (this) {
            this.subscriber = null;
            this.document = null;
            this.save();
        }
    }

    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.id = res.getInt("id");
        this.subscriber = (Abonne) EntityUtils.getEntityById(Abonne.class, res.getInt("idSubscriber"));
        this.document = (Document) EntityUtils.getEntityById(Document.class, res.getInt("idDocument"), false);

        this.date = res.getDate("date");

        this.document.setCommand(this);
        return this;
    }

    public String getIdentifier() {
        return PREFIX + id;
    }

    public void save() throws SQLException {
        synchronized (this) {
            (new CommandModel()).save(this);
        }
    }
}
