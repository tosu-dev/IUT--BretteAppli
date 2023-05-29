package App.Server.Entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Entity {

    Entity setFromResultSet(ResultSet res) throws SQLException;
}
