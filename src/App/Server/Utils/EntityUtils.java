package App.Server.Utils;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.BigDataManager;

import java.util.Comparator;
import java.util.List;

public class EntityUtils {

    public static List<Entity> getEntitiesByClassName(Class<? extends Entity> className) {
        return BigDataManager.getMap().values().stream().filter(entity -> className.isAssignableFrom(entity.getClass())).sorted(Comparator.comparingInt(Entity::getId)).toList();
    }

    public static Entity getEntityById(Class<? extends Entity> className, int id) {
        return EntityUtils.getEntitiesByClassName(className).stream().filter(entity -> entity.getId() == id).findFirst().orElse(null);
    }

    public static String showEntityListToString(Class<? extends Entity> entityClass) {
        StringBuilder sb         = new StringBuilder();
        List<Entity>  entityList = EntityUtils.getEntitiesByClassName(entityClass);

        entityList.forEach(entity -> sb.append(entity.toString()).append(System.lineSeparator()));

        return sb.toString();
    }

}
