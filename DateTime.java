import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public String formatted_date() {
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter_data = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sformatowanaData = data.format(formatter_data);
        return sformatowanaData;
    }
    public String formatted_time() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm:ss");
        String sformatowanaGodzina = time.format(formatter_time);
        return sformatowanaGodzina;
    }
}
