package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Interfaces.Entity;

import java.sql.SQLException;

public class SubscriberModel extends Model {

    public SubscriberModel() {
        super();
    }

    public void save(Entity entity) throws SQLException {
        return;
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
