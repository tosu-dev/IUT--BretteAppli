package App.Server.Entities.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Entity {

    int getId();

    Entity setFromResultSet(ResultSet res) throws SQLException;

    String getIdentifier();

    void save() throws SQLException;

}
