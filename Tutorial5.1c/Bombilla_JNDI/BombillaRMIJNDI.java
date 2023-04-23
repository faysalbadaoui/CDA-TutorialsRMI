import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BombillaRMIJNDI extends Remote, Serializable
{
	public void on() throws RemoteException;
	public void off() throws RemoteException;
	public boolean isOn() throws RemoteException;

	public String getTemp() throws RemoteException;
	public String getCons() throws RemoteException;

	public void modifyCons(int val) throws RemoteException;
	public void modifyTemp(int val) throws RemoteException;
	public void subscribe(ClientCallbacksJNDI cl) throws RemoteException;

}

