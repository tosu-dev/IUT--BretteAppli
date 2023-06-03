package App.Server.Models;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Managers.BigDataManager;
import App.Server.Managers.DatabaseManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public abstract class Model {

    protected Connection connection;

    public Model() {
        connection = DatabaseManager.connect();
    }


    public abstract void save(Entity entity) throws SQLException;


    public Entity getById(int id) {
        ResultSet res = getResultForId(getTableName(), id);
        try {
            if (res.next()) {
                return getEntityFromResultSet(res);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public Vector<Entity> getAll() {
        Vector<Entity> vector = new Vector<>();
        ResultSet      res    = getResultForAll(getTableName());
        try {
            while (res.next()) {
                vector.add(getEntityFromResultSet(res));
            }

            return vector;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public void send() throws SQLException {
        Vector<Entity> entities = this.getAll();
        entities.forEach(BigDataManager::add);
    }

    protected abstract String getTableName();

    protected abstract Entity getEntityInstance();


    private ResultSet getResultForId(String tableName, int id) {
        try {
            String            reqString = "SELECT * FROM bretteapplidb." + tableName + " WHERE id = ?";
            PreparedStatement req       = connection.prepareStatement(reqString);
            req.setString(1, Integer.toString(id));

            return req.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet getResultForAll(String tableName) {
        try {
            String reqString = "SELECT * FROM bretteapplidb." + tableName;

            return connection.createStatement().executeQuery(reqString);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Entity getEntityFromResultSet(ResultSet res) throws SQLException {
        Entity entity = getEntityInstance();
        return entity.setFromResultSet(res);
    }
}
