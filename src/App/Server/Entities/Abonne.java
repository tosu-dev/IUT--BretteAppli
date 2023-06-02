package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Models.SuscriberModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Abonne implements Entity {

    private final static String PREFIX = "SUB-";

    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;

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

    @Override
    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.id = res.getInt("id");
        this.firstname = res.getString("firstname");
        this.lastname = res.getString("lastname");
        this.birthdate = res.getDate("birthdate");

        return this;
    }

    @Override
    public String getIdentifier() {
        return PREFIX + id;
    }

    @Override
    public void save() throws SQLException {
        new SuscriberModel().save(this);
    }
}
