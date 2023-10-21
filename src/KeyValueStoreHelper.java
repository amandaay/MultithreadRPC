import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Helper function to store the Key-value pair
 */
public class KeyValueStoreHelper {
    private final ConcurrentHashMap<String, String> operation = new ConcurrentHashMap<>();

    /**
     * Handles get operator
     * @param key Key value intended for retrieval
     * @param getCount to keep track of 5 count or not
     * @return the response statement that we want to return to the client
     */
    public synchronized String get(String key, int getCount) {
        System.out.println(Utils.getCurrentTimestamp() + ", Receiving: PUT " + key);
        if (!operation.containsKey(key)) {
            return "Key does not exist.";
        } else {
            operation.get(key);
            getCount++;
            return getCount + "GETs*Here is your value " + operation.get(key);
        }
    }

    /**
     * Handles put operator
     * @param key Key value for storage
     * @param value value intended to store
     * @param putCount to keep track of 5 count or not
     * @return the response statement that we want to return to the client
     */
    public synchronized String put(String key, String value, int putCount) {
        System.out.println(Utils.getCurrentTimestamp() + ", Receiving: PUT " + key + " " + value);
        if (operation.containsKey(key)) {
            return "Key already exist, try another key.";
        } else {
            operation.put(key, value);
            putCount++;
            return putCount + "PUTs*OK saved operation: {key= " + key + ", value= " + value + "}\n" + "Current operations: " + operation;
        }
    }

    /**
     * Handles del operator
     * @param key Key value intended for removal
     * @param delCount to keep track of 5 count or not
     * @return the response statement that we want to return to the client
     */
    public synchronized String delete(String key, int delCount) {
        System.out.println(Utils.getCurrentTimestamp() + ", Receiving: DELETE " + key);
        if (!operation.containsKey(key)) {
            return "Key does not exist.\noperations left: " + operation;
        } else {
            operation.remove(key);
            delCount++;
            return delCount + "DELETEs*Deleted key as requested.\nOperations left: "+ operation;
        }
    }

    /**
     * Handles shutdown operator
     * @return shutdown request
     */
    public synchronized String shutdown(int getCount, int putCount, int delCount) {
        System.out.println(Utils.getCurrentTimestamp() + ", Receiving: SHUTDOWN");
        if (putCount < 5 || getCount < 5 || delCount < 5) {
            return "SHUTDOWN*You haven't completed at least 5 operator each. Are you sure you want to shutdown? (y/n)";
        }
        return "SHUTDOWN*Are you sure you want to shutdown? (y/n)";
    }

    /**
     * Handles confirm shutdown operator
     * @return shutdown request
     */
    public synchronized String confirmShutdown(String prevInput, String confirmation) {
        System.out.println(Utils.getCurrentTimestamp() + ", Receiving: SHUTDOWN confirmation");
        if (!prevInput.equalsIgnoreCase("SHUTDOWN") || !confirmation.equalsIgnoreCase("Y")) {
            return "confirmClose*Let's continue";
        }
        return "confirmClose*Shutting down as requested.\nYou must kill the server to shut down completely.";
    }

}