package logging;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

//3.7 We passen de smallLogFormatter toe op de log files en in de console
public class SmallLogFormatter extends Formatter {
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public String format(LogRecord record) {
        LocalDateTime ldt = LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        String message = MessageFormat.format(record.getMessage(), record.getParameters());
        return String.format("%s Level: %s melding: \"%s\"\n",
                ldt.format(DateTimeFormatter.ofPattern(DATE_FORMAT)), record.getLevel(), message);
    }
}
