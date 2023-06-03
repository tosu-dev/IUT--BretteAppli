package App.Server.Timers;

public interface ITimer extends Runnable {

    String getName();
    int getDuration();

}
