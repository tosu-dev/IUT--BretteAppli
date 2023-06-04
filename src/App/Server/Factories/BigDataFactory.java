package App.Server.Factories;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.BigDataManager;
import App.Server.Models.*;

import java.sql.SQLException;
import java.util.Vector;

public class BigDataFactory {

    private static final Vector<Model> models = new Vector<>();

    public static void populate() {
        models.add(new DocumentModel());
        models.add(new DvdModel());
        models.add(new SubscriberModel());
        models.add(new CommandModel());

        for (Model model: models) {
            model.send();
        }
    }

    public static void save() {
        try {
            for (Entity entity:  BigDataManager.getAll())
                entity.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
