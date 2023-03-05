/**
 * Dean Beebe
 * CS6650
 * Project 2
 * 
 * This file contains the client code for Project 2. Multiple instances of this code can be run in order to access the server key-value store in parallel.
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ClientRMI
 * 
 * Client class that connects to the RMI registry, accepts user input, and 
 */
public class ClientRMI {
    // private constructor
    private ClientRMI() {}

    public static void main(String[] args) {
        try {
            // registry object connecting to localhost, where serverRMI has set up a registry.
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            // stub as a local rep to execute methods on the server
            RMIInterface stub = (RMIInterface) registry.lookup("RMIInterface");
            Scanner scan = new Scanner(System.in);

            /*
             * while loop that accepts user input, then parses the input to get the 
             * user's command. Afterward, it correctly calls the remote method relating to 
             * the command and prints out the response. 
             */
            while (true) {

                System.out.println("Enter a command (put, get, or delete). Alternatively, type 'q' to quit: ");
                String userInput = scan.nextLine();
                String[] command = userInput.split(" ");

                // quit command for user to end client program.
                if(command[0].toLowerCase().equals("q")) {
                    break;
                } else {
                    //synchronized helps to enable mutual exclusivity in the event that multiple clients are attempting to access the server simultaneously.
                    synchronized(stub) {
                        switch(command[0].toLowerCase())
                        {
                            case "get":
                                String getResponse = stub.get(command[1]);
                                System.out.println(getResponse);
                                break;
                            case "put":
                                String value = String.join(" ", Arrays.copyOfRange(command, 2, command.length));
                                String putResponse = stub.put(command[1], value);
                                System.out.println(putResponse);
                                break;
                            case "delete":
                                String deleteResponse = stub.delete(command[1]);
                                System.out.println(deleteResponse);
                                break;
                        }
                    }
                }
            }
            // preventing Scanner leak (IDE suggested I do so (?)).
            scan.close();
        } catch (Exception e) {
            System.out.println("Exception on Client side: " + e.toString());
        }
    }
}
