package App.Server.Exceptions;

public class IllegalReturnException extends CustomException{
    @Override
    public String errorMessage() {
        return "Ce document est toujours en médiathèque, vous ne pouvez pas le retourner.";
    }
}
