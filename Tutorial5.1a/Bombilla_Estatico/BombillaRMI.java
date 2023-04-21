
import java.rmi.*;

public interface BombillaRMI extends java.rmi.Remote
{
	public void on() throws RemoteException;
	public void off() throws RemoteException;
	public boolean isOn() throws RemoteException;

	public int getTemp() throws RemoteException;
	public int getCons() throws RemoteException;

	public void modifyCons(int val) throws RemoteException;
	public void modifyTemp(int val) throws RemoteException;
}

