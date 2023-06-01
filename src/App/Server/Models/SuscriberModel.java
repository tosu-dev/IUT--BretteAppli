package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Entity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class SuscriberModel extends Model {

    public SuscriberModel() {
        super();
    }

    @Override
    public void save() throws SQLException {
        // nothing
    }

    @Override
    public String getTableName() {
        return "suscriber";
    }

    @Override
    public Entity getEntityInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> suscriberClass = Abonne.class;
        return (Entity) suscriberClass.getConstructor().newInstance();
    }
}
