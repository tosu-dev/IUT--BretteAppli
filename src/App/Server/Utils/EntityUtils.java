package App.Server.Utils;

import App.Server.Entities.Document;
import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.BigDataManager;

import java.util.List;
import java.util.stream.Stream;

public class EntityUtils {

    public static List<Entity> getEntitiesByClassName(Class<? extends Entity> className, boolean excludeDocument) {
        Stream<Entity> entityStream = BigDataManager.getMap().values().stream().filter(entity -> className.isAssignableFrom(entity.getClass()));
        if (excludeDocument) {
            entityStream = entityStream.filter(entity -> !Document.class.getName().equals(entity.getClass().getName()));
        }

        return entityStream.toList();
    }

    public static Entity getEntityById(Class<? extends Entity> className, int id, boolean excludeDocument) {
        return EntityUtils.getEntitiesByClassName(className, excludeDocument).stream().filter(entity -> entity.getId() == id).findFirst().orElse(null);
    }

    public static String showEntityListToString(Class<? extends Entity> entityClass, boolean excludeDocument) {
        StringBuilder sb         = new StringBuilder();
        List<Entity>  entityList = EntityUtils.getEntitiesByClassName(entityClass, excludeDocument);

        entityList.forEach(entity -> sb.append(entity.toString()).append(System.lineSeparator()));

        return sb.toString();
    }

    public static Entity getEntityById(Class<? extends Entity> className, int id) {
        return EntityUtils.getEntityById(className, id, true);
    }

    public static String showEntityListToString(Class<? extends Entity> entityClass) {
        return EntityUtils.showEntityListToString(entityClass, true);
    }

}
