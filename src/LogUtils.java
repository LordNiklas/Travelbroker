import java.text.SimpleDateFormat;
import java.util.Date;

class LogUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String log(String message) {
        String timestamp = dateFormat.format(new Date());
        String logMessage = timestamp + " - " + message;
        System.out.println(logMessage);
        return logMessage;
    }
}
