package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Command;
import App.Server.Entities.Document;
import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.DatabaseManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandModel extends Model {

    private void deleteCommand(int commandId) throws SQLException {
        PreparedStatement res = DatabaseManager.connect().prepareStatement("DELETE FROM command WHERE id = ?");
        res.setInt(1, commandId);

        res.executeUpdate();
        res.close();
    }

    private void editCommand(int commandId, Abonne subscriber, Document document, Date date) throws SQLException {
        PreparedStatement res = DatabaseManager.connect().prepareStatement("UPDATE command SET idSubscriber = ?, idDocument = ?, date = ? WHERE id = ?");
        res.setInt(1, subscriber.getId());
        res.setInt(2, document.getId());
        res.setDate(3, date);
        res.setInt(4, commandId);

        res.executeUpdate();
        res.close();
    }

    public void save(Entity entity) throws SQLException {

        Command command = (Command) entity;

        int id = command.getId();
        Abonne subscriber = command.getSubscriber();
        Document document = command.getDocument();
        Date date = command.getDate();

        if (subscriber == null || document == null) {
            this.deleteCommand(id);
            return;
        }

        this.editCommand(id, subscriber, document, date);
    }

    protected String getTableName() {
        return "command";
    }

    protected Entity getEntityInstance() {
        return new Command();
    }
}
