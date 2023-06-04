package App.Server.Models;

import App.Server.Entities.Dvd;
import App.Server.Entities.Interfaces.Entity;
import java.sql.SQLException;

public class DvdModel extends Model {

    public void save(Entity entity) throws SQLException {
    }

    protected String getTableName() {
        return "dvd";
    }

    protected Entity getEntityInstance() {
        return new Dvd();
    }
}
