package App.Server.Factories;

import App.Server.Entities.AbstractDocument;
import App.Server.Managers.ReminderManager;
import App.Server.Utils.EntityUtils;

import java.util.Vector;

public class ReminderFactory {

    public static void populate() {
        EntityUtils.getEntitiesByClassName(AbstractDocument.class, true).forEach(document -> ReminderManager.getReminders().put((AbstractDocument) document, new Vector<>()));
    }

}
