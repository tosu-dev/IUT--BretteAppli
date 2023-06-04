package App.Server.Timers;

import App.Server.Entities.Document;

public class ReservationTimer extends AbstractTimer {
    private final  Document         reservedDocument;
    private static ReservationTimer timerInstance;

    public ReservationTimer(Document reservedDocument) {
        this.reservedDocument = reservedDocument;
    }

    public static ReservationTimer getInstance() {
        if (timerInstance == null) {
            timerInstance = new ReservationTimer(null);
        }

        return timerInstance;
    }

    public void run() {
        if (this.reservedDocument != null) {
            this.reservedDocument.resetReservation();
        }
    }

    public String getName() {
        return "RESERVATION_TIMER";
    }

    public long getDuration() {
        return 60000 * 120; //2h
    }
}
