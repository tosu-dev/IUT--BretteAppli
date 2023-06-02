package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Interfaces.Entity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class SuscriberModel extends Model {

    public SuscriberModel() {
        super();
    }

    public void save(Entity entity) throws SQLException {
        return;
    }

    @Override
    public String getTableName() {
        return "suscriber";
    }

    @Override
    public Entity getEntityInstance() {
        return new Abonne();
    }
}
