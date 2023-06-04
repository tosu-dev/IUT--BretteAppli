package App.Server.Exceptions;

import App.Server.Entities.Interfaces.Entity;

public abstract class CustomException extends RuntimeException{

    private final Entity relatedEntity;

    public CustomException(Entity relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    public CustomException() {
        this(null);
    }

    public Entity getRelatedEntity() {
        return relatedEntity;
    }

    public abstract String errorMessage();

}
