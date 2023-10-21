import java.rmi.*;

public interface KeyValueStore extends Remote {
    String get(String key, int getCount) throws RemoteException;
    String put(String key, String value, int putCount) throws RemoteException;
    String delete(String key, int delCount) throws RemoteException;
    String shutdown(int getCount, int putCount, int delCount) throws RemoteException;
    String confirmShutdown(String prevInput, String confirmation) throws RemoteException;
}
