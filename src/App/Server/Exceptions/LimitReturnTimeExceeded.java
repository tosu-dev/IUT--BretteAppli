package App.Server.Exceptions;

public class LimitReturnTimeExceeded extends CustomException{
    @Override
    public String errorMessage() {
        return "Vous avez rendu ce document trop en retard. Vous allez être banni de nos service pendant un mois !";
    }
}
