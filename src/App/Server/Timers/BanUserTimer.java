package App.Server.Timers;

import App.Server.Entities.Abonne;
import App.Server.Entities.Document;

import java.sql.SQLException;

public class BanUserTimer extends AbstractTimer {
    private final  Abonne           bannedUser;
    private static BanUserTimer timerInstance;

    public BanUserTimer(Abonne bannedUser) {
        this.bannedUser = bannedUser;
    }

    public static BanUserTimer getInstance() {
        if (timerInstance == null) {
            timerInstance = new BanUserTimer(null);
        }

        return timerInstance;
    }

    public void run() {
        if (this.bannedUser != null) {
            try {
                this.bannedUser.unban();
            } catch (SQLException ignored) {
            }
        }
    }

    public String getName() {
        return "BAN_USER_TIMER";
    }

    //one month in milliseconds
    public long getDuration() {
        return 2592000000L; //1m and two weeks
    }
}
