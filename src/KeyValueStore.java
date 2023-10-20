import java.rmi.*;

public interface KeyValueStore extends Remote {
    String get(String key) throws RemoteException;
    void put(String key, String value) throws RemoteException;
    void delete(String key) throws RemoteException;
}
