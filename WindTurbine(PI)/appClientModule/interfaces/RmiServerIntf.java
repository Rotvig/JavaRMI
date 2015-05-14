package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

import models.Enviroment;
import models.WindTurbine;
 
public interface RmiServerIntf extends Remote {
    public WindTurbine getWindTurbineStatus() throws RemoteException;
    public Enviroment getEnviromentStatus() throws RemoteException;
}
