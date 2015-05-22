
import interfaces.RmiServerIntf;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject; 
import java.util.Random;

import models.Enviroment;
import models.WindTurbine;
@SuppressWarnings("serial")
public class RmiServerWindTurbineStatus extends UnicastRemoteObject implements RmiServerIntf, Runnable {
 
    public RmiServerWindTurbineStatus() throws RemoteException {
        super(0);    // required to avoid the 'rmic' step, see below
    }
    
    public void StartServer(String endPoint) throws RemoteException, MalformedURLException
    {
        System.out.println("WindTurbineStatus RMI server started");
        
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
 
        // Bind this object instance to the name "RmiServer"
        Naming.rebind(endPoint, this);
        System.out.println("PeerServer bound in registry");
    }

	@Override
	public WindTurbine getWindTurbineStatus(Enviroment environment) throws RemoteException {
		Random random = new Random();
		WindTurbine windTurbine = new WindTurbine();
		windTurbine.setId(1);
		windTurbine.setRPM(environment.getWindSpeed());
		windTurbine.setOrientation(environment.getWindDirection());
		windTurbine.setProduction(environment.getWindSpeed() + 10);
		windTurbine.setBladePitch(environment.getWindSpeed() * 5);
		windTurbine.setTemperature(environment.getWindSpeed() + 50);
		return windTurbine;
	}

	@Override
	public Enviroment getEnviromentStatus() throws RemoteException {
		Random random = new Random();
		Enviroment environment =  new Enviroment();
		environment.setWindDirection(random.nextFloat()*360);
		environment.setWindSpeed(random.nextFloat()*50);
		return environment;
	}

	@Override
	public void NewWindTurbineData(Enviroment environment)
			throws RemoteException {
        RmiServerIntf obj = null;
		try {
			obj = (RmiServerIntf)Naming.lookup("//localhost/EnvironmentStatus");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("WindDirection: " + environment.getWindDirection());
		System.out.println("Speed: " + environment.getWindSpeed());
        obj.NewWindTurbineData(environment);	
	}

	@Override
	public void NewEnviromentData() throws RemoteException {
        RmiServerIntf obj = null;
		try {
			obj = (RmiServerIntf)Naming.lookup("//localhost/EnvironmentStatus");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        this.NewWindTurbineData(obj.getEnviromentStatus());	
	}

	@Override
	public void run() {
		try {
			this.StartServer("//localhost/WindturbineStatus");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
