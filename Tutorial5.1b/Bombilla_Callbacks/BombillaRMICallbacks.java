
import java.rmi.*;

public interface BombillaRMICallbacks extends java.rmi.Remote
{
	public void on() throws RemoteException;
	public void off() throws RemoteException;
	public boolean isOn() throws RemoteException;

	public String getTemp() throws RemoteException;
	public String getCons() throws RemoteException;

	public void modifyCons(int val) throws RemoteException;
	public void modifyTemp(int val) throws RemoteException;
	public void subscribe(ClientDist cl) throws RemoteException;

}

