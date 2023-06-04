package App.Server.Entities;

public enum DocumentState {
    FREE(1, "LIBRE"),
    RESERVED(2, "RÉSERVÉ"),
    BORROWED(3, "EMPRUNTÉ"),
    UNKNOWN(404, "INCONNU");

    private final int stateId;
    private final String stateString;

    DocumentState(int stateId, String stateString) {
        this.stateId = stateId;
        this.stateString = stateString;
    }

    public final int getId() {
        return this.stateId;
    }

    public final String getState() {
        return this.stateString;
    }

    public static DocumentState fromInt(int stateId) {
        for (DocumentState documentState : DocumentState.values()) {
            if (documentState.getId() == stateId) {
                return documentState;
            }
        }
        return null;
    }
}
