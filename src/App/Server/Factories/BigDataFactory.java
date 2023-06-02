package App.Server.Factories;

import App.Server.Entities.Command;
import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.BigDataManager;
import App.Server.Models.CommandModel;
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
        models.add(new CommandModel());

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
