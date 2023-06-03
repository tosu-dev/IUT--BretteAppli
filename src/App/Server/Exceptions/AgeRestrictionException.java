package App.Server.Exceptions;

public class AgeRestrictionException extends CustomException{
    @Override
    public String errorMessage() {
        return "Vous n'avez pas l'âge requis pour ce livre.";
    }
}
