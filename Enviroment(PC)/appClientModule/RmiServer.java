
import interfaces.RmiServerIntf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; 

import models.Enviroment;
import models.WindTurbine;
@SuppressWarnings("serial")
public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
 
    public RmiServer() throws RemoteException {
        super(0);    // required to avoid the 'rmic' step, see below
    }

	@Override
	public WindTurbine getWindTurbineStatus() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enviroment getEnviromentStatus() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
