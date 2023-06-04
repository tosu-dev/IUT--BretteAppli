package App.Server.Exceptions;

import App.Server.Entities.Document;
import App.Server.Entities.Interfaces.Entity;

public class IllegalBorrowException extends CustomException {

    public IllegalBorrowException(Entity entity) {
        super(entity);
        assert Document.class.isAssignableFrom(entity.getClass());
    }

    @Override
    public String errorMessage() {
        //Todo, show final date to book :p
        Document document = (Document) this.getRelatedEntity();

        return "Ce document est déjà emprunté, veuillez réessayer plus tard.";
    }
}
