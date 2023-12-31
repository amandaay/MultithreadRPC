import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility functions to avoid repeated code
 */
public class Utils {
    /**
     * Getting Current Timestamps
     * @return current timestamp
     */
    public static String getCurrentTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        return dateFormat.format(new Date(currentTimeMillis));
    }

    /**
     * Counts operators for at least 5 operators to work
     * @param response response we get from the server
     * @param count It can be either put count, get count, or del count
     * @param operator GET, PUT, or DELETE
     * @return put count, get count, or del count
     */
    public static int handleCount(String response, int count, String operator) {
        int countIndex = response.indexOf(operator);
        if (countIndex != -1 ) {
            count = Integer.parseInt(response.substring(0, countIndex));
        }
        return count;
    }
}