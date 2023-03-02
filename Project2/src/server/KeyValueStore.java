import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class KeyValueStore extends UnicastRemoteObject implements RMI_Interface {
    private HashMap<String, String> keyValueStore;

    public KeyValueStore() throws RemoteException {
        super();
        keyValueStore = new HashMap<String, String>();
        this.preLoadStore();
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

    public void preLoadStore() throws RemoteException {
        keyValueStore.put("Honda", "Civic");
        keyValueStore.put("Dodge", "Durango");
        keyValueStore.put("Mercedes", "C300");
        keyValueStore.put("Chevrolet", "Corvette");
        keyValueStore.put("Toyota", "RAV4");
    }


}
