package App.Server.Models;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Entities.Document;
import App.Server.Managers.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentModel extends Model {

    public DocumentModel() {
        super();
    }

    @Override
    public void save(Entity entity) throws SQLException {
        Document doc = (Document) entity;

        int documentNumber = doc.getId();
        int documentState  = doc.getState();

        PreparedStatement res = DatabaseManager.connect().prepareStatement("UPDATE document SET state = ? WHERE id = ?");
        res.setInt(1, documentState);
        res.setInt(2, documentNumber);

        res.executeUpdate();

        DatabaseManager.commit();
    }

    @Override
    public String getTableName() {
        return "document";
    }

    @Override
    public Entity getEntityInstance() {
        return new Document();
    }
}
