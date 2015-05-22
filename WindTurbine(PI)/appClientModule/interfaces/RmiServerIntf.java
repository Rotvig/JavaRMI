package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;

import models.Enviroment;
import models.WindTurbine;
 
public interface RmiServerIntf extends Remote {
    public WindTurbine getWindTurbineStatus(Enviroment environment) throws RemoteException;
    public Enviroment getEnviromentStatus() throws RemoteException;
    public void NewWindTurbineData(Enviroment environment) throws RemoteException;
    public void NewEnviromentData() throws RemoteException;
}
