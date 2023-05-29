package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Entity;

import java.lang.reflect.InvocationTargetException;

public class SuscriberModel extends Model {

    public SuscriberModel() {
        super();
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
