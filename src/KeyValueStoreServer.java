import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class KeyValueStoreServer extends UnicastRemoteObject implements KeyValueStore {
    private final KeyValueStoreHelper helper = new KeyValueStoreHelper();

    protected KeyValueStoreServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized String get(String key, int getCount) throws RemoteException {
        return helper.get(key, getCount);
    }

    @Override
    public synchronized String put(String key, String value, int putCount) throws RemoteException {
        return helper.put(key, value, putCount);
    }

    @Override
    public synchronized String delete(String key, int delCount) throws RemoteException {
        return helper.delete(key, delCount);
    }

    @Override
    public synchronized String shutdown(int getCount, int putCount, int delCount) throws RemoteException {
        return helper.shutdown(getCount, putCount, delCount);
    }

    @Override
    public synchronized String confirmShutdown(String prevInput, String confirmation) throws RemoteException {
        return helper.confirmShutdown(prevInput, confirmation);
    }

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
            e.printStackTrace();
        }
    }

}
