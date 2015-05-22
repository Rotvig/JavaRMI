import interfaces.RmiServerIntf;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import models.Enviroment;
import models.WindTurbine;


public class EnviromentMain {
    public static void main(String args[]) throws Exception {
        RmiServerEnvironment server = new RmiServerEnvironment();
        (new Thread(server)).start();
        for(;;)
        {
        	Thread.sleep(4000);
	        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/WindturbineStatus");
	        obj.NewEnviromentData();
        }
    }	
}
