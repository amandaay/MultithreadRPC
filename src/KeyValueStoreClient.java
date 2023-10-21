import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
            int putCount = 0;
            int getCount = 0;
            int delCount = 0;
            String key = "";
            String value = "";
            String response = "";
            String prevInput = "";
            String[] prepopulate = {"a", "b", "c", "d", "e", "f"};
            for (int i = 0; i < 5; i ++) {
                System.out.println("PUT " + prepopulate[i] + " " + prepopulate[i + 1]);
                response = keyValueStore.put(prepopulate[i], prepopulate[i + 1], putCount++);
                String timestamp = Utils.getCurrentTimestamp();
                System.out.println(timestamp + ", " + response.substring(response.indexOf("*") + 1));
            }
            // perform at least 5 GETs, 5 PUTs, 5 DELETES
            while (true) {
                // Get the current timestamp
                String timestamp = Utils.getCurrentTimestamp();
                System.out.println("Enter PUT <key> <value>, GET <key>, or DELETE <key>");
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
                            }
                        }
                        case "PUT" -> {
                            if (inputTokens[1] != null && inputTokens[2] != null && inputTokens.length == 3) {
                                key = inputTokens[1];
                                value = inputTokens[2];
                                response = keyValueStore.put(key, value, putCount);
                                putCount = Utils.handleCount(response, putCount, "PUTs");
                            }
                        }
                        case "DELETE" -> {
                            if (inputTokens[1] != null && inputTokens.length == 2) {
                                key = inputTokens[1];
                                response = keyValueStore.delete(key, delCount);
                                delCount = Utils.handleCount(response, delCount, "DELETEs");
                            }
                        }
                        case "SHUTDOWN" -> {
                            // Allow client to make sure if 5 operators are not done
                            response = keyValueStore.shutdown(getCount, putCount, delCount);
                            System.out.println(timestamp + ", Enter operation:\nPUT <key> <value> or GET <key> or DELETE <key> or SHUTDOWN");
                        }
                        case "Y" -> {
                            response = keyValueStore.confirmShutdown(prevInput, operation);
                        }
                        default -> System.out.println("Received an unknown operation. Try again");
                    }
                }
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
