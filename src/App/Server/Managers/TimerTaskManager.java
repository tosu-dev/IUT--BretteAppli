package App.Server.Managers;

import App.Server.Timers.AbstractTimer;

import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

public class TimerTaskManager {

    private static final Map<String, Timer> timerMap = new ConcurrentHashMap<>();

    public static void schedule(String name, AbstractTimer timer) {
        timerMap.put(name, new Timer());

        timerMap.get(name).schedule(timer, timer.getDuration());
    }

    public static void abort(String name) {

        Timer actualTimer;

        if((actualTimer = timerMap.get(name)) == null) {
            return;
        }

        actualTimer.cancel();
        timerMap.remove(name);
    }

    public static void remove(String name) {
        timerMap.remove(name);
    }

}
