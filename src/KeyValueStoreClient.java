import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class KeyValueStoreClient {
    public static void main(String[] args) {
        try {
            String localhost = args[0];
            int clientPort = Integer.parseInt(args[1]);
            Registry registry = LocateRegistry.getRegistry(localhost, clientPort);
            KeyValueStore keyValueStore = (KeyValueStore) registry.lookup("KeyValueStore");

            // perform at least 5 GETs, 5 PUTs, 5 DELETES

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
