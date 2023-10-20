public class ClientHandler implements Runnable {
    private KeyValueStore server;

    public ClientHandler(KeyValueStoreServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
