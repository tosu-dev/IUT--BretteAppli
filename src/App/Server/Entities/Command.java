package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Models.DocumentModel;
import App.Server.Models.CommandModel;
import App.Server.Models.SubscriberModel;
import App.Server.Utils.EntityUtils;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public int getId() {
        return this.id;
    }

    public Abonne getSubscriber() {
        return subscriber;
    }

    public Document getDocument() {
        return document;
    }

    public Date getDate() {
        return date;
    }

    public void remove() throws SQLException {
        this.subscriber = null;
        this.document = null;
        this.save();
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
        (new CommandModel()).save(this);
    }
}
