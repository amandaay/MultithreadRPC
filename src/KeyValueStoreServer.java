import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

/**
 * KeyValueStoreServer by using rmi to ensure remote procedural call
 */
public class KeyValueStoreServer extends UnicastRemoteObject implements KeyValueStore {
    private final KeyValueStoreHelper helper = new KeyValueStoreHelper();

    protected KeyValueStoreServer() throws RemoteException {
        super();
    }

    @Override
    public String get(String key, int getCount) throws RemoteException {
        return helper.get(key, getCount);
    }

    @Override
    public String put(String key, String value, int putCount) throws RemoteException {
        return helper.put(key, value, putCount);
    }

    @Override
    public String delete(String key, int delCount) throws RemoteException {
        return helper.delete(key, delCount);
    }

    @Override
    public String shutdown(int getCount, int putCount, int delCount) throws RemoteException {
        return helper.shutdown(getCount, putCount, delCount);
    }

    @Override
    public String confirmShutdown(String prevInput, String confirmation) throws RemoteException {
        return helper.confirmShutdown(prevInput, confirmation);
    }

    /**
     * The main method for the KeyValueStoreServer class.
     * This method sets up an RMI (Remote Method Invocation) server for a key-value store.
     *
     * @param args Command-line arguments, where args[0] is the port number on which the server should listen.
     */
    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println("Usage: java KeyValueStoreServer <serverPort>");
                System.exit(1);
            }
            int serverPort = Integer.parseInt(args[0]);
            // create an RMI registry on the inputted port
            Registry registry = LocateRegistry.createRegistry(serverPort);
            // create an instance of KeyValueStoreServer
            KeyValueStoreServer server = new KeyValueStoreServer();
            // binds the server object to the registry with the name "KeyValueStore"
            registry.rebind("KeyValueStore", server);
            System.out.println("Server is running...");

        } catch (RemoteException e) {
            // Handle any RemoteException that might occur during RMI setup
            e.printStackTrace();
        }
    }

}
