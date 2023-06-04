package App.Server.Utils;

public class IntegerUtils {

    public static Integer toInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
