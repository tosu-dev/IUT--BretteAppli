package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriberModel extends Model {

    public SubscriberModel() {
        super();
    }

    public void save(Entity entity) throws SQLException {
        Abonne subscriber = (Abonne) entity;

        int subscriberId = subscriber.getId();
        boolean subscriberBanState = subscriber.isBanned();

        PreparedStatement res = DatabaseManager.connect().prepareStatement("UPDATE subscriber SET isBanned = ?, bannedDate = NOW() WHERE id = ?");
        res.setBoolean(1, subscriberBanState);
        res.setInt(2, subscriberId);

        res.executeUpdate();

        DatabaseManager.commit();
    }

    @Override
    public String getTableName() {
        return "subscriber";
    }

    @Override
    public Entity getEntityInstance() {
        return new Abonne();
    }
}
