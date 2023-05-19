package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Managers.Manager;

import java.sql.*;

public class SuscriberModel {

    public static Abonne getById(int id) {
        try {
            Connection connect = Manager.connect();

            PreparedStatement req = connect.prepareStatement("SELECT * FROM bretteapplidb.suscriber WHERE id = ?");
            req.setString(1, Integer.toString(id));
            ResultSet res = req.executeQuery();

            Abonne suscriber = new Abonne();
            if (res.next()) {
                // TODO :  maybe mettre ça dans une méthode de suscriber qui prend un ResultSet en param
                suscriber.setId(res.getInt("id"));
                suscriber.setFirstname(res.getString("firstname"));
                suscriber.setLastname(res.getString("lastname"));
                suscriber.setBirthdate(res.getDate("birthdate"));
            }

            return suscriber;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
