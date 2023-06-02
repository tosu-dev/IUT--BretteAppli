package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Models.SubscriberModel;

import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Abonne implements Entity {

    private final static String PREFIX = "SUB-";

    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;

    private boolean isBanned;

    public Abonne() {}

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public boolean isBanned() { return isBanned; }

    @Override
    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.id = res.getInt("id");
        this.firstname = res.getString("firstname");
        this.lastname = res.getString("lastname");
        this.birthdate = res.getDate("birthdate");
        this.isBanned = res.getBoolean("isBanned");

        return this;
    }

    @Override
    public String getIdentifier() {
        return PREFIX + id;
    }

    @Override
    public void save() throws SQLException {
        new SubscriberModel().save(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Abonne.class) return false;

        return this.getId() == ((Abonne) obj).getId();
    }
}
