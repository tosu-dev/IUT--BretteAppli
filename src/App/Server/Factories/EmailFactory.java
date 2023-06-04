package App.Server.Factories;

import App.Server.Managers.EmailManager;

public class EmailFactory {

    public static void setup() {
        EmailManager.setHost("mail.mehdi-ali.me");
        EmailManager.setPort(587);
        EmailManager.setUsername("legrandwakantanka@mehdi-ali.me");
        EmailManager.setPassword("XXXXXXXXXXXXXXXXXXXXXXX");
    }

}
