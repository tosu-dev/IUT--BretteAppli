package App.Server.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    private static final int AGE_ADULT = 16;

    public static boolean hasAge(Date subscriberAge) {

            Calendar calendar = Calendar.getInstance();

            Date actualDate = calendar.getTime();
            calendar.setTime(actualDate);

            calendar.add(Calendar.YEAR, AGE_ADULT * -1);
            Date limitDate = calendar.getTime();

            return subscriberAge.before(limitDate) || subscriberAge.equals(limitDate);

    }

    public static long getTimeElapsedFromNow(Date date) {

        long differenceInMillis = System.currentTimeMillis() - date.getTime();

        return TimeUnit.MILLISECONDS.toMinutes(differenceInMillis);
    }
}
