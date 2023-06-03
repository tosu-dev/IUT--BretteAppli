package App.Server.Managers;

import App.Server.Entities.Interfaces.Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class BigDataManager {

    public final static Map<String, Entity> bigData = new ConcurrentHashMap<>();

    public static void add(Entity entity) {
        BigDataManager.bigData.put(entity.getIdentifier(), entity);
    }

    public static Map<String, Entity> getMap() {
        return BigDataManager.bigData;
    }

    public static List<Entity> getAll() {
        return new Vector<>(BigDataManager.bigData.values());
    }

    public static Entity getByIdentifier(String identifier) {
        return BigDataManager.bigData.get(identifier);
    }

}
