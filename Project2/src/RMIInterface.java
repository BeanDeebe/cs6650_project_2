/**
 * Dean Beebe
 * CS6650
 * Project 2
 * 
 * This is the RMI remote interface for the Server code to implement.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMIInterface
 * 
 * Interface that defines get/put/delete methods which ServerRMI will implement.
 */
public interface RMIInterface extends Remote {
    String put(String key, String value) throws RemoteException;

    String get(String key) throws RemoteException;

    String delete(String key) throws RemoteException;
}
