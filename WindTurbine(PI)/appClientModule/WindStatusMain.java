
import interfaces.RmiServerIntf;

import java.rmi.Naming;

import models.Enviroment;
 
public class WindStatusMain { 
    public static void main(String args[]) throws Exception {
        RmiServerWindTurbineStatus server = new RmiServerWindTurbineStatus();
        (new Thread(server)).start();
        
        for(;;);
    }
}