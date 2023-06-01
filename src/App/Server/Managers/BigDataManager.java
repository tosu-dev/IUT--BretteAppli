package App.Server.Managers;

import App.Server.Entities.Entity;
import App.Server.Factories.BigDataFactory;

import java.util.HashMap;
import java.util.Vector;

public class BigDataManager {

    public final static HashMap<String, Entity> bigData = new HashMap<>();

    public static void create() {
        BigDataFactory.create();
    }

    public static void add(Entity entity) {
        bigData.put(entity.getIdentifier(), entity);
    }

    public static Vector<Entity> getAll() {
        return new Vector<>(bigData.values());
    }

    public static Entity get(String identifier) {
        return bigData.get(identifier);
    }

}
