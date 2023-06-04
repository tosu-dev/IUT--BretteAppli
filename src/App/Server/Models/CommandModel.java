package App.Server.Models;

import App.Server.Entities.Abonne;
import App.Server.Entities.Command;
import App.Server.Entities.Document;
import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandModel extends Model {

    private void deleteCommand(int commandId) throws SQLException {
        PreparedStatement res = DatabaseManager.connect().prepareStatement("DELETE FROM bretteapplidb.command WHERE id = ?");
        res.setInt(1, commandId);

        res.executeUpdate();
        res.close();
    }

    private void addCommand(int commandId, Abonne subscriber, Document document, Command command) throws SQLException {
        try {
            this.deleteCommand(commandId);

        PreparedStatement res = DatabaseManager.connect().prepareStatement("INSERT INTO bretteapplidb.command(idSubscriber, idDocument, date) VALUES(?, ?, NOW())", Statement.RETURN_GENERATED_KEYS);
        res.setInt(1, subscriber.getId());
        res.setInt(2, document.getId());

        res.executeUpdate();

        ResultSet resultSet = res.getGeneratedKeys();
        if (resultSet.next()) {
            command.setId(resultSet.getInt(1));
        }

        res.close();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save(Entity entity) throws SQLException {
        Command command = (Command) entity;

        int id = command.getId();
        Abonne subscriber = command.getSubscriber();
        Document document = command.getDocument();

        if (subscriber == null || document == null) {
            this.deleteCommand(id);
            return;
        }

        this.addCommand(id, subscriber, document, command);
        DatabaseManager.commit();
    }

    protected String getTableName() {
        return "command";
    }

    protected Entity getEntityInstance() {
        return new Command();
    }
}
