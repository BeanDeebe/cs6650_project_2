import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServerRMI implements RMIInterface {
    private HashMap<String, String> keyValueStore;
    public ServerRMI() throws RemoteException{
        super();
        keyValueStore = new HashMap<String, String>();
        System.out.println("Preloading value store...");
        this.preLoadStore();
        System.out.println("value store contents:");
        keyValueStore.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " : " + entry.getValue());
        });
    }

    public void preLoadStore() throws RemoteException {
        keyValueStore.put("Honda", "Civic");
        keyValueStore.put("Dodge", "Durango");
        keyValueStore.put("Mercedes", "C300");
        keyValueStore.put("Chevrolet", "Corvette");
        keyValueStore.put("Toyota", "RAV4");
    }

    public String put(String key, String value) throws RemoteException {
        keyValueStore.put(key, value);

        return "Successfully added {" + key + " : " + value + "} to the value store";
    }

    public String get(String key) throws RemoteException {
        String result = keyValueStore.get(key);

        if (result == null) {
            return "Hmm... can't seem to find the key: " + key + " in the value store.";
        } else {
            return "Found: " + result;
        }
    }

    public String delete(String key) throws RemoteException {
        String result = keyValueStore.remove(key);

        if (result == null) {
            return "No key found, nothing to delete for : " + key;
        } else {
            return "Successfully deleted: " + key;
        }
    }
    public static void main(String[] args) {
        try {
            RMIInterface server = new ServerRMI();

            RMIInterface stub = (RMIInterface) UnicastRemoteObject.exportObject(server, 1099);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("RMIInterface", stub);

            System.out.println("Server Ready!");
        } catch (Exception e) {
            System.out.println("Exception on the server: " + e.toString());
            e.printStackTrace();
        }
    }
}
