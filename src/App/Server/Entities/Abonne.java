package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Models.SubscriberModel;
import App.Server.Utils.ageUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Abonne implements Entity {

    private final static String PREFIX = "SUB-";

    private int    id;
    private String firstname;
    private String lastname;
    private Date   birthdate;

    private boolean isBanned;

    public Abonne() {
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Object getFullName() {
        return this.firstname + " " + this.lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.id = res.getInt("id");
        this.firstname = res.getString("firstname");
        this.lastname = res.getString("lastname");
        this.birthdate = res.getDate("birthdate");
        this.isBanned = res.getBoolean("isBanned");

        return this;
    }

    public String getIdentifier() {
        return PREFIX + id;
    }

    public void save() throws SQLException {
        new SubscriberModel().save(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Abonne.class) return false;

        return this.getId() == ((Abonne) obj).getId();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        boolean isAdult = ageUtils.hasAge(this.getBirthdate());

        return sb.append(this.getId())
                 .append(" - ")
                 .append(this.getLastname())
                 .append(" ")
                 .append(this.getFirstname())
                 .append((isAdult) ? " [+16]" : " [-16]")
                 .toString();
    }
}
