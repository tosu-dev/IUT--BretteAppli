package App.Server.Factories;

import App.Server.Managers.DatabaseManager;

public class DatabaseFactory {

public static void setup() {
    DatabaseManager.setHost("localhost");
    DatabaseManager.setPort(3306);
    DatabaseManager.setUser("root");
    DatabaseManager.setPassword("");
    DatabaseManager.setDatabase("bretteapplidb");
}

}
