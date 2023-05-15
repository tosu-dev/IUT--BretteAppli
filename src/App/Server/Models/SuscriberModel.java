package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Managers.Manager;

import java.math.BigInteger;
import java.sql.*;

public class SuscriberModel {

    public static Abonne getById(BigInteger id) {
        try {
            Connection connect = Manager.connect();

            PreparedStatement req = connect.prepareStatement("SELECT * FROM bretteapplidb.suscriber WHERE id = ?");
            req.setString(1, id.toString());
            ResultSet res = req.executeQuery();

            Abonne suscriber = new Abonne();
            if (res.next()) {
                suscriber.setId(res.getBigDecimal("id").toBigInteger());
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
