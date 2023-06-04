package App.Server.Timers;

import App.Server.Entities.Abonne;
import App.Server.Entities.AbstractDocument;
import App.Server.Services.Reservation.Components.ReserveBookComponent;
import Librairies.Servers.Service;

public class StopMusicTimer extends AbstractTimer {
    private final AbstractDocument     document;
    private final Abonne               subscriber;
    private final ReserveBookComponent reserveBookComponent;
    private final  Service              service;
    private static StopMusicTimer       timerInstance;

    public StopMusicTimer(Service service, ReserveBookComponent reserveBookComponent, AbstractDocument document, Abonne subscriber) {
        this.document = document;
        this.subscriber = subscriber;
        this.service = service;
        this.reserveBookComponent = reserveBookComponent;
    }

    public static StopMusicTimer getInstance() {
        if (timerInstance == null) {
            timerInstance = new StopMusicTimer(null, null, null, null);
        }

        return timerInstance;
    }

    public void run() {
        reserveBookComponent.stopMusic(service, document, subscriber);
    }

    public String getName() {
        return "STOP_MUSIC_TIMER";
    }

    public long getDuration() {
        return 30000; // 30s
    }
}