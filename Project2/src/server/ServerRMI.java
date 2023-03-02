import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ServerRMI extends KeyValueStore {
    
    public ServerRMI() throws RemoteException{}
    public static void main(String[] args) {
        try {
            KeyValueStore server = new KeyValueStore();

            RMI_Interface stub = (RMI_Interface) UnicastRemoteObject.unexportObject(stub, force)

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RMI_Interface", stub);

            System.out.println("Server Ready!");
        } catch (Exception e) {
            System.out.println("Exception on the server: " + e.toString());
            e.printStackTrace();
        }
    }
}
