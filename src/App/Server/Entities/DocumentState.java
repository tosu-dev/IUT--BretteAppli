package App.Server.Entities;

public enum DocumentState {
    FREE(1),
    RESERVED(2),
    BORROWED(3),
    UNKNOWN(404);

    private final int stateId;

    DocumentState(int stateId) {
        this.stateId = stateId;
    }

    public int getId() {
        return this.stateId;
    }
}
