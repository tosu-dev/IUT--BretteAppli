package App.Server.Managers;

import App.Server.Entities.Abonne;
import App.Server.Entities.AbstractDocument;
import App.Server.Utils.EntityUtils;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ReminderManager {

    private static final Map<AbstractDocument, List<Abonne>> reminders = new ConcurrentHashMap<>();

    public static Map<AbstractDocument, List<Abonne>> getReminders() {
        return reminders;
    }

    public static List<Abonne> getReminders(AbstractDocument document) {
        return reminders.get(document);
    }

    public static void addReminder(AbstractDocument document, Abonne abonne) {
        reminders.get(document).add(abonne);
    }

    public static void removeReminder(AbstractDocument document, Abonne abonne) {
        ReminderManager.reminders.get(document).remove(abonne);
    }

}
