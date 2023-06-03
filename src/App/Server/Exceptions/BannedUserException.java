package App.Server.Exceptions;

public class BannedUserException extends CustomException {
    @Override
    public String errorMessage() {
        return "L'utilisateur est banni de cette médiathèque. Ce service vous est interdit.";
    }
}
