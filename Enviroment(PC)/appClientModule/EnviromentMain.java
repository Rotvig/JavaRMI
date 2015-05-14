import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class EnviromentMain {
    public static void main(String args[]) throws Exception {
        System.out.println("EnviromentStatus RMI server started");
 
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
 
        //Instantiate RmiServer
 
        RmiServer obj = new RmiServer();
 
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/EnviromentStatus", obj);
        System.out.println("PeerServer bound in registry");
    }
}
