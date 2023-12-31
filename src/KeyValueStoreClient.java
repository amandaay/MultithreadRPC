import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * KeyValueStoreClient invoking the RMI server
 */
public class KeyValueStoreClient {
    public static void main(String[] args) {
        try {
            System.out.println("Starting the Key Value Store Client");
            if (args.length > 2) {
                System.out.println("Usage: java KeyValueStoreClient <localhost> <port number>");
                System.exit(1);
            }
            // parse the argument input
            String localhost = args[0];
            int clientPort = Integer.parseInt(args[1]);
            // locate the RMI registry on the hostname and port
            Registry registry = LocateRegistry.getRegistry(localhost, clientPort);
            // looks up key value store from the registry
            // and casts it to the KeyValueStore interface allowing client to invoke the remote methods
            // Establish connections to the remote KeyValueStore object on the server
            KeyValueStore keyValueStore = (KeyValueStore) registry.lookup("KeyValueStore");
            System.out.println("Enter at least 5 PUTs, 5 GETs, 5 DELETEs operation.\nHere is an example:");
            int putCount = 0, getCount = 0, delCount = 0;
            String key = null, value = null, response = "", prevInput = "", timestamp = "";
            // to pre-populate the Key-Value store with data and a set of keys
            String[] prepopulate = {"a", "b", "c", "d", "e", "f"};
            for (int i = 0; i < 5; i ++) {
                System.out.println("PUT " + prepopulate[i] + " " + prepopulate[i + 1]);
                response = keyValueStore.put(prepopulate[i], prepopulate[i + 1], putCount++);
                timestamp = Utils.getCurrentTimestamp();
                System.out.println(timestamp + ", " + response.substring(response.indexOf("*") + 1));
            }
            System.out.println(timestamp + ", Enter operation:\nPUT <key> <value> or GET <key> or DELETE <key> or SHUTDOWN");
            // perform at least 5 GETs, 5 PUTs, 5 DELETES
            while (true) {
                // Get the current timestamp
                timestamp = Utils.getCurrentTimestamp();
                String userInput = System.console().readLine();
                System.out.println(timestamp + ", Sending: " + userInput);

                // Split the user input
                String[] inputTokens = userInput.split("\\s+");
                if (inputTokens.length > 0) {
                    String operation = inputTokens[0].toUpperCase();

                    switch (operation) {
                        case "GET" -> {
                            if (inputTokens.length == 2) {
                                key = inputTokens[1];
                                response = keyValueStore.get(key, getCount);
                                getCount = Utils.handleCount(response, getCount, "GETs");
                            } else {
                                System.out.println("Make sure there's one key to perform GET operation.");
                            }
                        }
                        case "PUT" -> {
                            if (inputTokens.length == 3) {
                                key = inputTokens[1];
                                value = inputTokens[2];
                                response = keyValueStore.put(key, value, putCount);
                                putCount = Utils.handleCount(response, putCount, "PUTs");
                            } else {
                                System.out.println("Make sure there's one key value to perform PUT operation.");
                            }
                        }
                        case "DELETE" -> {
                            if (inputTokens.length == 2) {
                                key = inputTokens[1];
                                response = keyValueStore.delete(key, delCount);
                                delCount = Utils.handleCount(response, delCount, "DELETEs");
                            } else {
                                System.out.println("Make sure there's one key to perform DELETE operation.");
                            }
                        }
                        case "SHUTDOWN" -> {
                            if (inputTokens.length == 1) {
                                // Allow client to make sure if 5 operators are not done
                                response = keyValueStore.shutdown(getCount, putCount, delCount);
                            }
                        }
                        case "Y" -> {
                            if (inputTokens.length == 1) {
                                response = keyValueStore.confirmShutdown(prevInput, operation);
                            }
                        }
                        default -> System.out.println("Received an unknown operation. Try again");
                    }
                }
                System.out.println(timestamp + ", Enter operation:\nPUT <key> <value> or GET <key> or DELETE <key> or SHUTDOWN");
                int indexCleanResponse = response.indexOf("*");
                if (indexCleanResponse > 0) {
                    prevInput = response.substring(0, indexCleanResponse);
                }
                System.out.println(timestamp + ", " + response.substring(indexCleanResponse + 1));
                // Check if the client wants to terminate
                if (response.contains("Shutting down as requested.")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
