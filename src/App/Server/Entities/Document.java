package App.Server.Entities;

import App.Server.Entities.Interfaces.Entity;
import App.Server.Exceptions.BannedUserException;
import App.Server.Exceptions.IllegalBookingException;
import App.Server.Exceptions.IllegalBorrowException;
import App.Server.Exceptions.IllegalReturnException;
import App.Server.Managers.TimerTaskManager;
import App.Server.Models.DocumentModel;
import App.Server.Services.Borrow.BorrowService;
import App.Server.Timers.ReservationTimer;
import App.Server.Utils.TimeUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class Document extends AbstractDocument {

    private              int     state;
    private              Command command;
    protected final      Object  lock   = new Object();
    private final static String  PREFIX = "DOC-";

    private String getReservationTimerName() {
        return Document.PREFIX + ReservationTimer.getInstance().getName() + "-" + this.getId();
    }

    private void checkIfUserIsBanned(Abonne ab) {
        if (ab.isBanned()) {
            throw new BannedUserException();
        }
    }


    public void setState(DocumentState state) throws SQLException {
        synchronized (this.lock) {
            this.state = state.getId();
            this.save();
        }
    }

    public int getState() {
        synchronized (this.lock) {
            return this.state;
        }
    }

    public String getTitle() {
        synchronized (this.lock) {
            return this.title;
        }
    }

    public Command getCommand() {
        synchronized (this.lock) {
            return this.command;
        }
    }

    public void setCommand(Command command) {
        synchronized (this.lock) {
            this.command = command;
        }
    }

    public Abonne empruntePar() {
        if (this.getState() == DocumentState.BORROWED.getId()) {
            return this.command.getSubscriber();
        }

        return null;
    }

    public Abonne reservePar() {
        if (this.getState() == DocumentState.RESERVED.getId()) {
            return this.command.getSubscriber();
        }

        return null;
    }

    public void reservation(Abonne ab) {
        this.checkIfUserIsBanned(ab);

        if (this.getState() == DocumentState.BORROWED.getId()) {
            throw new IllegalBorrowException(this);
        }

        if (this.getState() == DocumentState.RESERVED.getId()) {
            throw new IllegalBookingException(this);
        }

        try {
            synchronized (this.lock) {
                this.command = new Command(ab, this, Calendar.getInstance().getTime());
                this.setState(DocumentState.RESERVED);
                TimerTaskManager.schedule(this.getReservationTimerName(), new ReservationTimer(this));
            }
        } catch (SQLException ignored) {

        }
    }

    public void emprunt(Abonne ab) {

        this.checkIfUserIsBanned(ab);

        if (this.getState() == DocumentState.BORROWED.getId()) {
            throw new IllegalBorrowException(this);
        }

        if (this.getState() == DocumentState.RESERVED.getId()) {
            if (!this.command.getSubscriber().equals(ab)) {
                throw new IllegalBookingException(this);
            }

            synchronized (this.lock) {
                TimerTaskManager.abort(this.getReservationTimerName());
            }
        }


        try {
            synchronized (this.lock) {
                this.command = new Command(ab, this, Calendar.getInstance().getTime());
                this.setState(DocumentState.BORROWED);
            }
        } catch (SQLException ignored) {

        }
    }

    public void retour() {

        if (this.getState() != DocumentState.BORROWED.getId()) {
            throw new IllegalReturnException();
        }

        try {
            Abonne subscriber = this.getCommand().getSubscriber();
            synchronized (this.lock) {
                this.command.remove();
                this.setState(DocumentState.FREE);

                if (TimeUtils.getTimeElapsedFromNow(this.getCommand().getDate()) >= BorrowService.BORROW_LIMIT_TIME) {
                    subscriber.ban();
                }
            }
        } catch (SQLException ignored) {

        }
    }

    public void resetReservation() {

        if (this.getState() != DocumentState.RESERVED.getId()) {
            return;
        }
        synchronized (this.lock) {
            TimerTaskManager.remove(this.getReservationTimerName());
            try {
                this.command.remove();
                this.setState(DocumentState.FREE);
            } catch (SQLException ignored) {
            }
        }

    }

    public Entity setFromResultSet(ResultSet res) throws SQLException {
        this.numero = res.getInt("id");
        this.title = res.getString("title");
        this.state = res.getInt("state");

        return this;
    }

    public String getIdentifier() {
        return PREFIX + numero;
    }

    public void save() throws SQLException {
        synchronized (this.lock) {
            new DocumentModel().save(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        DocumentState ds = DocumentState.fromInt(this.state);
        if (ds == null) ds = DocumentState.UNKNOWN;

        sb.append("[").append(ds.getState()).append("] ").append(this.getId()).append(" - ").append(this.title);

        return sb.toString();
    }
}
