
import interfaces.RmiServerIntf;

import java.rmi.Naming;

import models.Enviroment;
 
public class WindStatusMain { 
    public static void main(String args[]) throws Exception {
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/EnviromentStatus");
        Enviroment enviroment = obj.getEnviromentStatus();
        System.out.println(enviroment.getWindDirection()); 
        System.out.println(enviroment.getWindSpeed()); 
    }
}