import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KeyValueStoreServer extends UnicastRemoteObject implements KeyValueStore {
    private ConcurrentHashMap<String, String> dataStore = new ConcurrentHashMap<>();

    protected KeyValueStoreServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized String get(String key) throws RemoteException {
        // Implement the GET operation
        return null;
    }

    @Override
    public synchronized void put(String key, String value) throws RemoteException {
        // Implement PUT operation
    }

    @Override
    public synchronized void delete(String key) throws RemoteException {
        // Implement DELETE operation
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8000);
            KeyValueStoreServer server = new KeyValueStoreServer();
            registry.rebind("KeyValueStore", server);
            System.out.println("Server is running...");
            // Create an ExecutorService with a fixed pool of threads to handle incoming client requests
            // You can adjust the pool size as needed
             ExecutorService executor = Executors.newFixedThreadPool(10);

            while (true) {
                // Accept client connections and submit them to the executor
                // This allows handling multiple client requests concurrently
                // Runnable clientTask = new ClientHandler(server);
                // executor.execute(clientTask);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
