
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
public class RmiServerEnvironment extends UnicastRemoteObject implements RmiServerIntf, Runnable {
 
    public RmiServerEnvironment() throws RemoteException {
        super(0);    // required to avoid the 'rmic' step, see below
    }
    
    public void StartServer(String endPoint) throws RemoteException, MalformedURLException
    {
        System.out.println("EnviromentStatus RMI server started");
        
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
		windTurbine.setRPM(environment.getWindSpeed() - 10);
		windTurbine.setTemperature(environment.getWindSpeed() - 10);
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
	public void NewWindTurbineData(Enviroment environment) throws RemoteException {
        RmiServerIntf obj = null;
		try {
			obj = (RmiServerIntf)Naming.lookup("//localhost/WindturbineStatus");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        WindTurbine windTurbine = obj.getWindTurbineStatus(environment);
        
        System.out.println("id: " + windTurbine.getId());
        System.out.println("BladePitch: " + windTurbine.getBladePitch()); 
        System.out.println("Orientation: " + windTurbine.getOrientation()); 
        System.out.println("Production: " + windTurbine.getProduction()); 
        System.out.println("RPM:" + windTurbine.getRPM()); 
        System.out.println("Temperature: " + windTurbine.getTemperature());  		
	}

	@Override
	public void NewEnviromentData() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		try {
			this.StartServer("//localhost/EnvironmentStatus");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
