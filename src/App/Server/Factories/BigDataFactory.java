package App.Server.Factories;

import App.Server.Entities.Entity;
import App.Server.Managers.BigDataManager;
import App.Server.Models.DocumentModel;
import App.Server.Models.Model;
import App.Server.Models.SuscriberModel;

import java.sql.SQLException;
import java.util.Vector;

public class BigDataFactory {

    private static final Vector<Model> models = new Vector<>();

    public static void create() {
        models.add(new DocumentModel());
        models.add(new SuscriberModel());

        try {
            for (Model model: models)
                model.send();
        } catch (SQLException e) {
            e.printStackTrace();
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
