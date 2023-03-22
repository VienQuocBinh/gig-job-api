package gigjob.common.util;

import gigjob.common.exception.model.InternalServerErrorException;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DateTimeUtil {
    public String durationBetween(Date date1, Date date2) {
        DateTimeFormatter formatterDbDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.S]");
        DateTimeFormatter formatterUtilDate = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss zzz yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        try {
            LocalDateTime dbDate = LocalDateTime.parse(date1.toString(), formatterDbDate);
            LocalDateTime nowDate = LocalDateTime.parse(date2.toString(), formatterUtilDate);

            long minutesBetween = ChronoUnit.MINUTES.between(dbDate, nowDate);
            return decimalFormat.format((double) minutesBetween / 60);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error occurred while get duration between " + date1 + " and " + date2 +
                    "\n" +
                    e.getMessage());
        }
    }
}
